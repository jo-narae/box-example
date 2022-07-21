package com.example.box.controller;

import com.example.box.domain.Product;
import com.example.box.domain.ProductOption;
import com.example.box.dto.ProductDTO;
import com.example.box.mapper.ProductMapper;
import com.example.box.mapper.ProductOptionMapper;
import com.example.box.service.ProductOptionService;
import com.example.box.service.ProductService;
import com.example.box.util.CustomPageRequest;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductOptionService productOptionService;

    @GetMapping
    public Page<Product> list(@RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer purchasePrice,
                              @RequestParam(required = false) Integer salesPrice,
                              final CustomPageRequest pageable) {
        return productService.findProductList(name, purchasePrice, salesPrice, pageable.of());
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") int id) {
        return productService.findById(id);
    }

    @PostMapping
    public Product insertProduct(@RequestBody ProductDTO dto) {
        ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
        ProductOptionMapper mapper2 = Mappers.getMapper(ProductOptionMapper.class);

        Product product = productService.saveProduct(mapper.mappingProduct(dto, new Product()));

        List<ProductOption> options = mapper2.dtoListMapping(dto.getOptions());
        options.stream().forEach(option -> option.setProduct(product));

        productOptionService.saveProductOptions(options);

        return product;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") int id, @RequestBody ProductDTO dto) {
        Product origin = productService.findById(id);

        ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
        ProductOptionMapper mapper2 = Mappers.getMapper(ProductOptionMapper.class);

        Product product = productService.saveProduct(mapper.mappingProduct(dto, origin));

        List<ProductOption> options = mapper2.dtoListMapping(dto.getOptions());
        options.stream().forEach(option -> option.setProduct(product));

        productOptionService.saveProductOptions(options);

        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
    }
}
