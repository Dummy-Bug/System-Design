package com.lld;

import com.lld.criteria.*;
import com.lld.factories.PriceComparisonStrategyFactory;
import com.lld.models.Brand;
import com.lld.models.Category;
import com.lld.models.Product;
import com.lld.utils.Operator;

import java.util.List;

public class Main {
    static void main() {
        Brand b1 = new Brand("Apple");
        Brand b2 = new Brand("Samsung");
        Brand b3 = new Brand("OnePlus");

        Category c1 = new Category("Electronics");

        Product p1 = new Product("iPhone 12", 799.99, b1, c1);
        Product p2 = new Product("Galaxy S21", 1000.00, b2, c1);
        Product p3 = new Product("OnePlus 9", 699.99, b3, c1);

        List<Product> products = List.of(p1, p2, p3);

        Criteria cr1 = new BrandFilterCriteria("Apple");
        Criteria cr2 = new BrandFilterCriteria("Samsung");

        Criteria priceFilterCriteria = new PriceFilterCriteria(999,
                PriceComparisonStrategyFactory.create(Operator.LESS_THAN));
        Criteria orCriteria = new OrFilterCriteria(List.of(cr1, cr2));
        Criteria andCriteria = new AndFilterCriteria(List.of(orCriteria, priceFilterCriteria));
        List<Product> filteredProducts = andCriteria.satisfy(products);

        filteredProducts.forEach(product -> IO.println(product.getName()));
    }
}
