package com.lld.strategies;

public interface PriceComparisonStrategy {
    boolean compare(double productPrice, double filterPrice);
}
