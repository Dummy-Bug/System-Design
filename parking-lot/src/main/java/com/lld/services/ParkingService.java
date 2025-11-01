package com.lld.services;

import com.lld.factories.PriceCalculationFactory;
import com.lld.models.parking.Parking;
import com.lld.models.slot.ParkingSlot;
import com.lld.models.slot.SlotStatus;
import com.lld.models.ticket.Ticket;
import com.lld.models.vehicle.Vehicle;
import com.lld.strategies.slot.SlotFindingStrategy;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingService {
    Parking parking;
    SlotFindingStrategy slotFindingStrategy;
    Map<String, Vehicle> slotVehicleMap;
    Map<String, ParkingSlot> registrationNumberToSlotMap;
    Map<String, String> colorToRegistrationNumberMap;
    Map<String, Ticket> vehicleTicketMap;

    ParkingService(Parking parking, SlotFindingStrategy slotFindingStrategy) {
        this.parking = parking;
        this.slotFindingStrategy = slotFindingStrategy;
        this.slotVehicleMap = new HashMap<>();
        this.registrationNumberToSlotMap = new HashMap<>();
        this.colorToRegistrationNumberMap = new HashMap<>();
        this.vehicleTicketMap = new HashMap<>();
    }

    public boolean park(Vehicle vehicle) {
        Optional<ParkingSlot> slotOptional = slotFindingStrategy.findSlot(parking, vehicle);
        if (slotOptional.isPresent()) {
            ParkingSlot slot = slotOptional.get();
            slot.setStatus(SlotStatus.OCCUPIED);
            slot.setParkedVehicle(vehicle);
            this.slotVehicleMap.put(slot.getSlotId(), vehicle);
            this.registrationNumberToSlotMap.put(vehicle.getRegistrationNumber(), slot);
            this.colorToRegistrationNumberMap.put(vehicle.getColor(), vehicle.getRegistrationNumber());
        } else {
            throw new RuntimeException("Parking is full");
        }
        Ticket ticket = Ticket.builder()
                .ticketId("1")
                .vehicleEntryTime(Instant.now())
                .priceCalculationStrategy(PriceCalculationFactory.getPriceCalculationStrategyInstance(vehicle.getType()))
                .slot(slotOptional.get())
                .build();
        vehicleTicketMap.put(vehicle.getRegistrationNumber(), ticket);
        return true;
    }

    public void leaveParking(Vehicle vehicle) {

        ParkingSlot slot = registrationNumberToSlotMap.get(vehicle.getRegistrationNumber());
        if (slot == null) {
            System.out.println("Vehicle not found inside the parking lot");
            return;
        }
        slot.setStatus(SlotStatus.AVAILABLE);
        registrationNumberToSlotMap.remove(vehicle.getRegistrationNumber());

        Ticket ticket = vehicleTicketMap.get(vehicle.getRegistrationNumber());
        ticket.setVehicleExitTime(Instant.now());
        ticket.calculateAndPay();

        System.out.println("Vehicle left parking lot");
    }
}
