package com.lld.strategies.payment;

public class UpiPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(float price) {
        System.out.printf("UPI payment of RS %s success! %n", price);
        return true;
    }
}
