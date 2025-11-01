package com.lld.models.slot;

public class CarParkingSlot extends ParkingSlot {
    @Override
    public boolean park() {
        System.out.println("car parking success !");
        return true;
    }
}
