package com.lld.bookmyshow.services;

import com.lld.bookmyshow.models.ShowSeat;
import com.lld.bookmyshow.models.ShowSeatStatus;
import com.lld.bookmyshow.models.Ticket;
import com.lld.bookmyshow.repositories.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImplementation implements BookingService {

    private final CacheService cacheService;
    private final ShowSeatRepository showSeatRepository;

    public BookingServiceImplementation(CacheService cacheService, ShowSeatRepository showSeatRepository) {
        this.cacheService = cacheService;
        this.showSeatRepository = showSeatRepository;
    }

    @Override
    public boolean lockSeats(long showId, List<Long> seatIds, Long userId) {

        List<ShowSeat> showSeats = showSeatRepository.findAllByShowIdAndSeatIdIn(showId, seatIds);
        System.out.println("Printing showSeats");
        showSeats.forEach(showSeat -> {
            System.out.println(showSeat.getId() + " " + showSeat.getShowSeatStatus());
        });

        for (ShowSeat seat : showSeats) {
            if (seat.getShowSeatStatus().equals(ShowSeatStatus.BOOKED)) {
                return false;
            }
        }
        for (ShowSeat seat : showSeats) {
            String status = (String) cacheService.get("seatId-" + seat.getId() + "-showId-" + showId);
            if (status != null) {
                return false;
            }
        }
        for (ShowSeat seat : showSeats) {
            cacheService.set("seatId-" + seat.getId() + "-showId-" + userId, "LOCKED");
        }
        return true;
    }

    @Override
    public Optional<Ticket> bookTicket(long showId, List<Long> showSeatIds, long userId) {
        return Optional.empty();
    }

    @Override
    public void clearAllSeatLocks() {
        cacheService.deleteAll();
    }
}
