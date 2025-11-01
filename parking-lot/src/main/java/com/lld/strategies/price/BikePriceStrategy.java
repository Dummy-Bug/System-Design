package com.lld.strategies.price;

public class BikePriceStrategy implements PriceCalculationStrategy {
    @Override
    public float calculatePrice(int minutes) {
        return (float) 50 * minutes / 60;
    }
}
