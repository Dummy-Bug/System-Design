package com.lld.strategies.slot;

import com.lld.models.parking.Parking;
import com.lld.models.slot.ParkingSlot;
import com.lld.models.vehicle.Vehicle;

import java.util.Optional;

public class RandomSlotFindingStrategy implements SlotFindingStrategy {

    @Override
    public Optional<ParkingSlot> findSlot(Parking parking, Vehicle vehicle) {
        return Optional.empty();
    }

}
