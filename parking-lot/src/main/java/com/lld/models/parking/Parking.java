package com.lld.models.parking;

import com.lld.models.floor.Floor;
import lombok.Data;

import java.util.List;

@Data
public class Parking {
    List<Floor> floors;
}
