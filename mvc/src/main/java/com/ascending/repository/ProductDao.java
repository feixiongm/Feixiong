package com.ascending.repository;

import com.ascending.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProduct();
    Product save(Product product);
    Boolean deleteByname(String proName);
    Product getProductById(Long productId);
}
