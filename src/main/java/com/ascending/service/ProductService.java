package com.ascending.service;

import com.ascending.model.Product;
import com.ascending.repository.ProductDao;
import com.ascending.repository.ProductDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public Product save(Product product) { return productDao.save(product);}
    public Product update(Product product) { return productDao.save(product);}
    public Boolean deleteByName(String proName) { return productDao.deleteByname(proName); }
    public List<Product> getProducts() { return productDao.getProduct(); }
    public Product getProductById(Long productId) { return productDao.getProductById(productId); }
}
