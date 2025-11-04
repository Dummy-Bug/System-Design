package com.lld.strategies.payment;

public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(float price) {
        System.out.printf("Cash payment of RS %s success! %n", price);
        return true;
    }
}
