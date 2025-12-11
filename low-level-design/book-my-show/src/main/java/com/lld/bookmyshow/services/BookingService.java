package com.lld.bookmyshow.services;

import com.lld.bookmyshow.models.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookingService {

    boolean lockSeats(List<Long> showSeatIds, long userId);

    Optional<Ticket> confirmBooking(List<Long> showSeatIds, long userId);

    void clearAllSeatLocks();
}
