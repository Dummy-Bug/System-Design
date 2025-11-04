package com.lld.models.slot;

import com.lld.models.vehicle.VehicleType;

import java.util.List;

public class ElectricBikeParkingSlot extends ParkingSlot implements ElectricSlot {
    public ElectricBikeParkingSlot(String slotId, SlotStatus status) {
        this.slotId = slotId;
        this.status = status;
        this.supportedVehicles = List.of(VehicleType.ELECTRIC_BIKE);
    }
}
