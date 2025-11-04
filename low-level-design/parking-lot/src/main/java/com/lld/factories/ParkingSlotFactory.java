package com.lld.factories;

import com.lld.models.slot.BikeParkingSlot;
import com.lld.models.slot.CarParkingSlot;
import com.lld.models.slot.ElectricBikeParkingSlot;
import com.lld.models.slot.ElectricCarParkingSlot;
import com.lld.models.slot.ParkingSlot;
import com.lld.models.slot.SlotStatus;
import com.lld.models.vehicle.VehicleType;

public class ParkingSlotFactory {

    public static ParkingSlot createSlot(VehicleType type, String slotId, SlotStatus status) {
        return switch (type) {
            case CAR -> new CarParkingSlot(slotId, status);
            case BIKE -> new BikeParkingSlot(slotId, status);
            case ELECTRIC_CAR -> new ElectricCarParkingSlot(slotId, status);
            case ELECTRIC_BIKE -> new ElectricBikeParkingSlot(slotId, status);
            default -> throw new IllegalArgumentException("Unsupported vehicle type: " + type);
        };
    }
}