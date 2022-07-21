package com.example.box.repository;

import com.example.box.domain.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ProductSpec {
    public static Specification<Product> searchWith(Map<String, Object> searchKeyword) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            searchKeyword.forEach((key, value) -> {
                switch (key) {
                    case "name":
                        predicates.add(builder.like(root.get("name"), "%" + value + "%"));
                        break;
                    case "purchasePrice":
                        predicates.add(builder.lessThanOrEqualTo(root.get("purchasePrice"), value.toString()));
                        break;
                    case "salesPrice":
                        predicates.add(builder.lessThanOrEqualTo(root.get("salesPrice"), value.toString()));
                        break;
                }
            });

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
