package com.tony.springbootmybatiswithredis.controller;

import com.tony.springbootmybatiswithredis.dao.domain.Product;
import com.tony.springbootmybatiswithredis.dao.mapper.ProductMapper;
import com.tony.springbootmybatiswithredis.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/{id}")
    public Product getProductInfo(
            @PathVariable("id")
                    Long productId) {
        return productMapper.select(productId);
    }

    @PutMapping("/{id}")
    public Product updateProductInfo(
            @PathVariable("id")
                    Long productId,
            @RequestBody
                    Product newProduct) {
        Product product = productMapper.select(productId);
        if (product == null) {
            throw new ProductNotFoundException(productId);
        }
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        productMapper.update(product);
        return product;
    }
}
