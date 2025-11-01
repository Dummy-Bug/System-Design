package com.lld.strategies.payment;

public class FastTagPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(float price) {
        System.out.printf("FAS-TAG payment of RS %s success! %n", price);
        return true;
    }
}
