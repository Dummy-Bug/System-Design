package com.lld.models.slot;

import com.lld.models.vehicle.Vehicle;
import com.lld.models.vehicle.VehicleType;
import lombok.Data;

import java.util.List;

@Data
public abstract class ParkingSlot {
    String slotId;
    List<VehicleType> supportedVehicles;
    SlotStatus status;
    Vehicle parkedVehicle;
}
