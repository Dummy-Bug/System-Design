package com.lld.criteria;

import com.lld.models.Product;

import java.util.ArrayList;
import java.util.List;

public class AndFilterCriteria implements Criteria {

    private final List<Criteria> criteriaList;

    public AndFilterCriteria(List<Criteria> criteria) {
        this.criteriaList = criteria;
    }

    @Override
    public List<Product> satisfy(List<Product> products) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            boolean allMatch = true;
            for (Criteria criteria : criteriaList) {
                if (criteria.satisfy(List.of(product)).isEmpty()) {
                    allMatch = false;
                    break;
                }
            }
            if (allMatch) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}
