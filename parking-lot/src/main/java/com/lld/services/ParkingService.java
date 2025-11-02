package com.lld.services;

import com.lld.factories.PriceCalculationFactory;
import com.lld.models.parking.Parking;
import com.lld.models.slot.ParkingSlot;
import com.lld.models.slot.SlotStatus;
import com.lld.models.ticket.Ticket;
import com.lld.models.vehicle.Vehicle;
import com.lld.models.vehicle.VehicleType;
import com.lld.strategies.slot.SlotFindingStrategy;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingService {
    public Parking parking;
    public SlotFindingStrategy slotFindingStrategy;
    public Map<String, Vehicle> slotVehicleMap;
    public Map<String, ParkingSlot> registrationNumberToSlotMap;
    public Map<String, String> colorToRegistrationNumberMap;
    public Map<String, Ticket> vehicleTicketMap;
    public PricingService pricingService;

    public ParkingService(Parking parking, SlotFindingStrategy slotFindingStrategy) {
        this.parking = parking;
        this.slotFindingStrategy = slotFindingStrategy;
        this.slotVehicleMap = new HashMap<>();
        this.registrationNumberToSlotMap = new HashMap<>();
        this.colorToRegistrationNumberMap = new HashMap<>();
        this.vehicleTicketMap = new HashMap<>();
        this.pricingService = new PricingService();
    }

    public void park(Vehicle vehicle) {
        Optional<ParkingSlot> slotOptional = slotFindingStrategy.findSlot(parking, vehicle);
        if (slotOptional.isPresent()) {
            ParkingSlot slot = slotOptional.get();
            slot.setStatus(SlotStatus.OCCUPIED);
            slot.setParkedVehicle(vehicle);
            this.slotVehicleMap.put(slot.getSlotId(), vehicle);
            this.registrationNumberToSlotMap.put(vehicle.getRegistrationNumber(), slot);
            this.colorToRegistrationNumberMap.put(vehicle.getColor(), vehicle.getRegistrationNumber());
            System.out.println(vehicle.getType() + " has been parked in " + slot.getSlotId());
        } else {
            throw new RuntimeException("Parking is full");
        }
        Ticket ticket = Ticket.builder()
                .ticketId("1")
                .vehicleEntryTime(Instant.now().minusSeconds(120))
                .priceCalculationStrategy(PriceCalculationFactory.getPriceCalculationStrategyInstance(vehicle.getType()))
                .slot(slotOptional.get())
                .build();
        vehicleTicketMap.put(vehicle.getRegistrationNumber(), ticket);
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
        ticket.setPricingService(pricingService);
        ticket.calculateAndPay();

        System.out.printf("\n %s left parking lot \n",vehicle.getType());
    }
}
