package com.lld.services;

import com.lld.decorator.BasePrice;
import com.lld.decorator.DiscountDecorator;
import com.lld.decorator.PriceComponent;
import com.lld.decorator.TaxDecorator;
import com.lld.models.ticket.Ticket;
import com.lld.strategies.price.PriceCalculationStrategy;

import java.time.Duration;

public class PricingService {
    private static final double TAX_PERCENTAGE = 18.0;
    private static final double DISCOUNT_PERCENTAGE = 10.0;


    public double calculateFinalPrice(Ticket ticket) {
        PriceCalculationStrategy strategy = ticket.getPriceCalculationStrategy();
        double basePrice = strategy.calculatePrice(Duration.between(ticket.getVehicleExitTime(),ticket.getVehicleEntryTime()).getSeconds());

        PriceComponent component = new BasePrice(basePrice);
        component = new TaxDecorator(component, TAX_PERCENTAGE);
        component = new DiscountDecorator(component, DISCOUNT_PERCENTAGE);

        return component.getPrice();
    }
}
