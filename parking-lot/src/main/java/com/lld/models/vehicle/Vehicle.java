package com.lld.models.vehicle;

import lombok.Data;

@Data
public abstract class Vehicle {
    String registrationNumber;
    String color;
    VehicleType type;

}
