package com.lld.strategies.slot;

import com.lld.models.floor.Floor;
import com.lld.models.parking.Parking;
import com.lld.models.slot.ParkingSlot;
import com.lld.models.slot.SlotStatus;
import com.lld.models.vehicle.Vehicle;
import com.lld.models.vehicle.VehicleType;

public class LinearSlotFindingStrategy implements SlotFindingStrategy {
    @Override
    public ParkingSlot findSlot(Parking parking, Vehicle vehicle) {
        for (Floor floor : parking.getFloors()) {
            for (ParkingSlot slot : floor.getSlots()) {
                if (slot.getStatus().equals(SlotStatus.AVAILABLE)) {
                    for (VehicleType vehicleType : slot.getSupportedVehicles()) {
                        if (vehicleType.equals(vehicle.getType())) {
                            System.out.println("found slot " + slot.getSlotId());
                            return slot;
                        }
                    }
                }
            }
        }
        throw new RuntimeException("Parking is full Inconvenience is regretted");
    }
}
