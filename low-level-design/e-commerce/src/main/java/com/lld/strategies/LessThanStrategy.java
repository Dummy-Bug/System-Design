package com.lld.strategies;

public class LessThanStrategy implements PriceComparisonStrategy {
    @Override
    public boolean compare(double productPrice, double filterPrice) {
        return productPrice < filterPrice;
    }
}
