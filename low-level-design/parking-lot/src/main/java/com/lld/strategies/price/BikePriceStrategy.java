package com.lld.strategies.price;

public class BikePriceStrategy implements PriceCalculationStrategy {
    @Override
    public double calculatePrice(long seconds) {
        double minutes = seconds / 60.0;
        double ratePerMinute = 1.0;
        double price = minutes * ratePerMinute;
        return Math.round(price * 100.0) / 100.0;
    }
}
