package com.lld.criteria;

import com.lld.models.Product;

import java.util.ArrayList;
import java.util.List;

public class OrFilterCriteria implements Criteria {
    private final List<Criteria> criteriaList;

    public OrFilterCriteria(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    @Override
    public List<Product> satisfy(List<Product> products) {

        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            boolean anyMatch = false;
            for (Criteria criteria : criteriaList) {
                if (!criteria.satisfy(List.of(product)).isEmpty()) {
                    anyMatch = true;
                    break;
                }
            }
            if (anyMatch) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}
