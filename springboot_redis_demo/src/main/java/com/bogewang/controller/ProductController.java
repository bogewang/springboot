package com.bogewang.controller;

import com.bogewang.entity.Product;
import com.bogewang.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bogewang on 2017/6/1.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/{id}")
    public Product getProductInfo(@PathVariable("id") Long productId){
        return productMapper.select(productId);
    }

    @PutMapping("/{id}")
    public Product updateProductInfo(@PathVariable("id") Long productId, @RequestBody Product newProduct){
        return null;
    }
}
