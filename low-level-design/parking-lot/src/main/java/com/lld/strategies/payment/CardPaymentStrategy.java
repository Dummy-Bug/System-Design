package com.lld.strategies.payment;

public class CardPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(float price) {
        System.out.printf("CARD payment of RS %s success! %n", price);
        return true;
    }
}
