package com.lld.decorator;

public class DiscountDecorator implements PriceComponent {
    private final PriceComponent base;
    private final double discountPercentage;

    public DiscountDecorator(PriceComponent base, double discountPercentage) {
        this.base = base;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double getPrice() {
        return base.getPrice() * (1 - discountPercentage / 100.0);
    }
}

