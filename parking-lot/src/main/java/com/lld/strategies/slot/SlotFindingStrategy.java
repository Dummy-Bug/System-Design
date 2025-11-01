package com.lld.strategies.slot;

import com.lld.models.parking.Parking;
import com.lld.models.slot.ParkingSlot;
import com.lld.models.vehicle.Vehicle;

public interface SlotFindingStrategy {
    public ParkingSlot findSlot(Parking parking, Vehicle vehicle);
}
