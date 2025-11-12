package com.lld.criteria;

import com.lld.models.Product;
import com.lld.strategies.PriceComparisonStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class PriceFilterCriteria implements Criteria {

    private final double filtererPrice;
    private final PriceComparisonStrategy comparisonStrategy;

    public PriceFilterCriteria(double filtererPrice, PriceComparisonStrategy comparisonStrategy) {
        this.filtererPrice = filtererPrice;
        this.comparisonStrategy = comparisonStrategy;
    }

    @Override
    public List<Product> satisfy(List<Product> products) {
        return products.stream()
                .filter(product ->
                        comparisonStrategy.compare(product.getPrice(), filtererPrice))
                .collect(Collectors.toList());
    }
}
