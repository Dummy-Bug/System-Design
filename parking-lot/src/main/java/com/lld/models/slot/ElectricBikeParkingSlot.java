package com.lld.models.slot;

public class ElectricBikeParkingSlot extends ParkingSlot implements ElectricSlot {
    @Override
    public boolean park() {
        System.out.println("Electric Bike parking success !");
        return true;
    }
}
