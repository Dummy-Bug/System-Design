package com.lld.strategies.slot;

import com.lld.models.parking.Parking;
import com.lld.models.slot.ParkingSlot;
import com.lld.models.vehicle.Vehicle;

import java.util.Optional;

public interface SlotFindingStrategy {
    public Optional<ParkingSlot> findSlot(Parking parking, Vehicle vehicle);
}
