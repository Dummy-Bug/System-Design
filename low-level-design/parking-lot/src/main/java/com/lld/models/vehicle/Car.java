package com.lld.models.vehicle;

public class Car extends Vehicle {
    public Car(String registrationNumber, String color, VehicleType type) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.type = type;
    }
}
