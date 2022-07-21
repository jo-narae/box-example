package com.example.box.repository;

import com.example.box.domain.History;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class HistorySpec {
    public static Specification<History> searchWith(Map<String, Object> searchKeyword) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            searchKeyword.forEach((key, value) -> {
                switch (key) {
                    case "type":
                        predicates.add(builder.equal(root.get("type"), value));
                        break;
                }
            });

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
