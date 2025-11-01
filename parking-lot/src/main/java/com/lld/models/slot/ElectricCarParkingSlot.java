package com.lld.models.slot;

public class ElectricCarParkingSlot extends ParkingSlot implements ElectricSlot {
    @Override
    public boolean park() {
        System.out.println("Electric car parking success !");
        return true;
    }
}
