package com.lld.models.floor;

import com.lld.models.slot.ParkingSlot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Floor {
    String floorId;
    List<ParkingSlot> slots;
}
