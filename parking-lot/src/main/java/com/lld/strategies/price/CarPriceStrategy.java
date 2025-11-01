package com.lld.strategies.price;

public class CarPriceStrategy implements PriceCalculationStrategy {

    @Override
    public float calculatePrice(int minutes) {
        return (float) 100 * minutes / 60;
    }
}
