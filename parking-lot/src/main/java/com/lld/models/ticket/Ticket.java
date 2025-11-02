package com.lld.models.ticket;

import com.lld.models.slot.ParkingSlot;
import com.lld.models.vehicle.Vehicle;
import com.lld.services.PricingService;
import com.lld.strategies.price.PriceCalculationStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    String ticketId;
    Instant vehicleEntryTime;
    Instant vehicleExitTime;
    ParkingSlot slot;
    Vehicle vehicle;
    PriceCalculationStrategy priceCalculationStrategy;
    PricingService pricingService;

    public void calculateAndPay() {
        double finalPrice = this.pricingService.calculateFinalPrice(this);
        System.out.printf("total amount is %s", finalPrice);
        // simulate the pay and all which should be trivial
    }
}
