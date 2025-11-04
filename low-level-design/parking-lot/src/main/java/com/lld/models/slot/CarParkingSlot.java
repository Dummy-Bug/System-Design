package com.lld.models.slot;

import com.lld.models.vehicle.VehicleType;

import java.util.List;

public class CarParkingSlot extends ParkingSlot {
    public CarParkingSlot(String slotId, SlotStatus status) {
        this.slotId = slotId;
        this.status = status;
        this.supportedVehicles = List.of(VehicleType.CAR);
    }
}
