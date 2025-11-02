package com.lld.strategies.price;

public class CarPriceStrategy implements PriceCalculationStrategy {

    @Override
    public double calculatePrice(long seconds) {
        double minutes = seconds / 60.0;
        double ratePerMinute = 2.0;
        double price = minutes * ratePerMinute;
        return Math.round(price * 100.0) / 100.0;
    }
}
