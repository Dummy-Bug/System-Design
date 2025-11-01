package com.lld.models.vehicle;

public class ElectricCar implements ElectricVehicle {
    @Override
    public void chargeVehicle() {
        System.out.println("Car charging started");
    }
}
