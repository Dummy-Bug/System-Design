package com.lld.models.slot;

import com.lld.models.vehicle.VehicleType;

import java.util.List;

public class BikeParkingSlot extends ParkingSlot {
    public BikeParkingSlot(String slotId, SlotStatus status) {
        this.slotId = slotId;
        this.status = status;
        this.supportedVehicles = List.of(VehicleType.BIKE);
    }
}
