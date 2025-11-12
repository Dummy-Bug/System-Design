package com.lld.factories;

import com.lld.strategies.GreaterThanStrategy;
import com.lld.strategies.LessThanStrategy;
import com.lld.strategies.PriceComparisonStrategy;
import com.lld.utils.Operator;

public class PriceComparisonStrategyFactory {

    public static PriceComparisonStrategy create(Operator comparisonType) {
        if (comparisonType.equals(Operator.GREATER_THAN)) {
            return new GreaterThanStrategy();
        } else if (comparisonType.equals(Operator.LESS_THAN)) {
            return new LessThanStrategy();
        }
        return null;
    }
}
