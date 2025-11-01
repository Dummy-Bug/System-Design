package com.lld.services;

import com.lld.models.parking.Parking;
import com.lld.models.slot.ParkingSlot;
import com.lld.models.slot.SlotStatus;
import com.lld.models.vehicle.Vehicle;
import com.lld.strategies.slot.SlotFindingStrategy;

import java.util.Optional;

public class ParkingService {
    Parking parking;
    SlotFindingStrategy slotFindingStrategy;

    ParkingService(Parking parking, SlotFindingStrategy slotFindingStrategy) {
        this.parking = parking;
        this.slotFindingStrategy = slotFindingStrategy;
    }

    public boolean park(Vehicle vehicle) {
        Optional<ParkingSlot> slotOptional = slotFindingStrategy.findSlot(parking, vehicle);
        if (slotOptional.isPresent()) {
            ParkingSlot slot = slotOptional.get();
            slot.setStatus(SlotStatus.OCCUPIED);
            slot.setParkedVehicle(vehicle);
            if (slot.park()) {
                System.out.println("Parked Successfully");
            } else {
                System.out.println("Not able to park");
            }
        }
        return true;
    }
}
