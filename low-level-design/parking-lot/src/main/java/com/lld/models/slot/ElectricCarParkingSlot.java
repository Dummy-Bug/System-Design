package com.lld.models.slot;

import com.lld.models.vehicle.VehicleType;

import java.util.List;

public class ElectricCarParkingSlot extends ParkingSlot implements ElectricSlot {

    public ElectricCarParkingSlot(String slotId, SlotStatus status) {
        this.slotId = slotId;
        this.status = status;
        this.supportedVehicles = List.of(VehicleType.ELECTRIC_CAR);
    }
}
