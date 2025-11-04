package com.lld.models.vehicle;

public class ElectricBike implements ElectricVehicle {
    @Override
    public void chargeVehicle() {
        System.out.println("Bike charging started");
    }
}
