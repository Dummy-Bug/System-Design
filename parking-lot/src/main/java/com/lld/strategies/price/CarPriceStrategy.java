package com.lld.strategies.price;

public class CarPriceStrategy implements PriceCalculationStrategy {

    @Override
    public double calculatePrice(long minutes) {
        return (double) 100 * minutes / 60;
    }
}
