package com.lld.decorators;

public class TaxDecorator implements PriceComponent {
    private final PriceComponent base;
    private final double taxPercentage;

    public TaxDecorator(PriceComponent base, double taxPercentage) {
        this.base = base;
        this.taxPercentage = taxPercentage;
    }

    @Override
    public double getPrice() {
        return base.getPrice() * (1 + taxPercentage / 100.0);
    }
}
