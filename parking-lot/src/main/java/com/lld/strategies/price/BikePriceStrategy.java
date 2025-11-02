package com.lld.strategies.price;

public class BikePriceStrategy implements PriceCalculationStrategy {
    @Override
    public double calculatePrice(long minutes) {
        return (double) 50 * minutes / 60;
    }
}
