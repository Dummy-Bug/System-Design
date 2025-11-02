package com.lld.models.parking;

import com.lld.models.floor.Floor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parking {
    List<Floor> floors;
}
