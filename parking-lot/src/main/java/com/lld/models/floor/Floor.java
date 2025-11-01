package com.lld.models.floor;

import com.lld.models.slot.ParkingSlot;
import lombok.Data;

import java.util.List;

@Data
public class Floor {
    String floorId;
    List<ParkingSlot> slots;
}
