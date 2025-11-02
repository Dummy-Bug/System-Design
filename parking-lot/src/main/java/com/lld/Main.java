package com.lld;

import com.lld.factories.ParkingSlotFactory;
import com.lld.models.floor.Floor;
import com.lld.models.parking.Parking;
import com.lld.models.slot.ParkingSlot;
import com.lld.models.slot.SlotStatus;
import com.lld.models.vehicle.Bike;
import com.lld.models.vehicle.Car;
import com.lld.models.vehicle.Vehicle;
import com.lld.models.vehicle.VehicleType;
import com.lld.services.ParkingService;
import com.lld.strategies.slot.LinearSlotFindingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ParkingSlot> slots = new ArrayList<>();

        slots.add(ParkingSlotFactory.createSlot(VehicleType.CAR, "S1", SlotStatus.AVAILABLE));
        slots.add(ParkingSlotFactory.createSlot(VehicleType.BIKE, "S2", SlotStatus.AVAILABLE));
        slots.add(ParkingSlotFactory.createSlot(VehicleType.ELECTRIC_CAR, "S3", SlotStatus.AVAILABLE));
        slots.add(ParkingSlotFactory.createSlot(VehicleType.ELECTRIC_BIKE, "S4", SlotStatus.AVAILABLE));
        slots.add(ParkingSlotFactory.createSlot(VehicleType.CAR, "S5", SlotStatus.AVAILABLE));

        Floor floor = Floor.builder()
                .floorId("f1")
                .slots(slots)
                .build();
        // Step 2: Create a Parking instance
        Parking parking = new Parking(List.of(floor));

        // Step 3: Choose a slot-finding strategy
        LinearSlotFindingStrategy slotStrategy = new LinearSlotFindingStrategy();

        // Step 4: Create the parking service
        ParkingService parkingService = new ParkingService(parking, slotStrategy);

        // Step 5: Create some vehicles
        Vehicle car = new Car("DL-8CAF-1234", "Black", VehicleType.CAR);
        Vehicle bike = new Bike("HP-09-AB-3321", "Red", VehicleType.BIKE);

        // Step 6: Park vehicles
        System.out.println("Parking car...");
        parkingService.park(car);
        System.out.println("Parking bike...");
        parkingService.park(bike);

        // Step 7: Wait or simulate some parking duration
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Step 8: Leave parking and calculate charges
        System.out.println("/n /n Car leaving...");
        parkingService.leaveParking(car);

        System.out.println("Bike leaving...");
        parkingService.leaveParking(bike);
    }
}