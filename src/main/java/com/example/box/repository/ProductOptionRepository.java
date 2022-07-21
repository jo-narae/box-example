package com.example.box.repository;

import com.example.box.domain.Product;
import com.example.box.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Integer> {
}
