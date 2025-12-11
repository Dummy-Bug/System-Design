package com.lld.bookmyshow.services;

import com.lld.bookmyshow.models.*;
import com.lld.bookmyshow.repositories.ShowSeatRepository;
import com.lld.bookmyshow.repositories.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.lld.bookmyshow.models.ShowSeatStatus.AVAILABLE;

@Service
public class BookingServiceImplementation implements BookingService {

    private final CacheService cacheService;
    private final ShowSeatRepository showSeatRepository;
    private final TicketRepository ticketRepository;

    public BookingServiceImplementation(CacheService cacheService, ShowSeatRepository showSeatRepository, TicketRepository ticketRepository) {
        this.cacheService = cacheService;
        this.showSeatRepository = showSeatRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<ShowSeat> showAvailableSeats(long showId) {
        List<ShowSeat> showSeats = showSeatRepository.findAllByShowId(showId);
        List<ShowSeat> availableShowSeats = new ArrayList<>();

        for (ShowSeat showSeat : showSeats) {
            if (showSeat.getShowSeatStatus().equals(AVAILABLE)) {
                availableShowSeats.add(showSeat);
            }
        }
        return availableShowSeats;
    }

    @Override
    public boolean lockSeats(List<Long> showSeatIds, long userId) {

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        if (showSeats.isEmpty() || showSeats.size() != showSeatIds.size()) {
            System.out.println("Invalid seat selection.");
            return false;
        }

        List<String> acquiredKeys = new ArrayList<>();

        for (ShowSeat seat : showSeats) {
            long showId = seat.getShow().getId();
            long seatId = seat.getSeat().getId();

            String key = "seat:" + showId + ":" + seatId;

            boolean ok = cacheService.setIfAbsent(key, String.valueOf(userId), 120);

            if (!ok) {
                // rollback
                acquiredKeys.forEach(cacheService::delete);

                System.out.printf("Seat %s already locked by another user%n",
                        seat.getSeat().getSeatNumber());
                return false;
            }

            acquiredKeys.add(key);
        }

        acquiredKeys.forEach(k -> System.out.println("Lock acquired: " + k));
        return true;
    }

    @Override
    @Transactional
    public Optional<Ticket> confirmBooking(List<Long> showSeatIds, long userId) {

        // Fetch seats
        List<ShowSeat> seats = showSeatRepository.findAllById(showSeatIds);
        if (seats.isEmpty() || seats.size() != showSeatIds.size()) {
            System.out.println("Invalid seats for booking.");
            return Optional.empty();
        }

        // Validate user lock ownership
        for (ShowSeat seat : seats) {
            String key = "seat:" + seat.getShow().getId() + ":" + seat.getSeat().getId();

            Object redisValue = cacheService.get(key);
            if (redisValue == null) {
                System.out.println("Seat lock expired or missing for seatId " + seat.getSeat().getId());
                return Optional.empty();
            }

            String lockedBy = redisValue.toString();

            if (!lockedBy.equals(String.valueOf(userId))) {
                System.out.println("User does not own lock for seatId " + seat.getSeat().getId());
                return Optional.empty();
            }
        }

        // DB availability validation
        for (ShowSeat seat : seats) {
            if (!seat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                System.out.println("Seat already booked: " + seat.getSeat().getSeatNumber());
                return Optional.empty();
            }
        }

        User userRef = new User();
        userRef.setId(userId);

        Ticket ticket = new Ticket();
        ticket.setUser(userRef);
        ticket.setShow(seats.get(0).getShow());
        ticket.setStatus(TicketStatus.BOOKED);
        ticket.setAmount(100 * showSeatIds.size());

        ticket = ticketRepository.save(ticket);

        // Mark seats as booked
        for (ShowSeat seat : seats) {
            seat.setTicket(ticket);
            seat.setShowSeatStatus(ShowSeatStatus.BOOKED);
        }
        // save all locked seats inside DB
        showSeatRepository.saveAll(seats);

        // Release locks
        for (ShowSeat seat : seats) {
            String key = "seat:" + seat.getShow().getId() + ":" + seat.getSeat().getId();
            cacheService.delete(key);
        }
        return Optional.of(ticket);
    }


    @Override
    public void clearAllSeatLocks() {
        cacheService.deleteAll();
    }
}
