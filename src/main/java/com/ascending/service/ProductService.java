package com.ascending.service;

import com.ascending.model.Product;
import com.ascending.repository.ProductDao;
import com.ascending.repository.ProductDaoImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductDao productDao = new ProductDaoImpl();

    public Product save (Product product){
        return productDao.save(product);
    }
    public Boolean delete(String proName){
        return productDao.delete(proName);
    }
    public List<Product> getLocations(){
        return productDao.getProduct();
    }
}
