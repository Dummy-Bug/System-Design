package com.lld.models.slot;

public class BikeParkingSlot extends ParkingSlot {
    @Override
    public boolean park() {
        System.out.println("Bike parking success");
        return true;
    }
}
