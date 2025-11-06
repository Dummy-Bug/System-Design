package com.lld.bookmyshow.services;

import com.lld.bookmyshow.models.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookingService {

    boolean lockSeats(long showId, List<Long> seatIds, Long userId);

    Optional<Ticket> bookTicket(long showId, List<Long> showSeatIds, long userId);

    void clearAllSeatLocks();
}
