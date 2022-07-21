package com.example.box.service;

import com.example.box.domain.Product;
import com.example.box.domain.ProductOption;
import com.example.box.repository.ProductOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductOptionService {

    @Autowired
    ProductOptionRepository productOptionRepository;

    public void saveProductOptions(List<ProductOption> productOptions) {
        productOptionRepository.saveAll(productOptions);
    }

    public ProductOption findById(int id) {
        return productOptionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
