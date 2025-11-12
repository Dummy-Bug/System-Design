package com.lld.criteria;

import com.lld.models.Product;

import java.util.List;

public interface Criteria {
    List<Product> satisfy(List<Product> products);
}
