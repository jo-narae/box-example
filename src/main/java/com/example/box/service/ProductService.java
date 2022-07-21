package com.example.box.service;

import com.example.box.domain.Product;
import com.example.box.repository.ProductRepository;
import com.example.box.repository.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Page<Product> findProductList(String name, Integer purchasePrice, Integer salesPrice, PageRequest pageable) {
        Map<String, Object> searchKeys = new HashMap<>();
        if (name != null) searchKeys.put("name", name);
        if (purchasePrice != null) searchKeys.put("purchasePrice", purchasePrice);
        if (salesPrice != null) searchKeys.put("salesPrice", salesPrice);

        Specification<Product> spec = Specification.where(ProductSpec.searchWith(searchKeys));
        return productRepository.findAll(spec, pageable);
    }

    public Product findById(int id) {
        return productRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
