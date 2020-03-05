package com.ascending.repository;

import com.ascending.init.ApplicationBootstrap;
import com.ascending.model.Location;
import com.ascending.model.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class ProductDaoTest {
    private Product product;
    private Location location;
    @Autowired
    LocationDao locationDao;
    @Autowired
    private ProductDao productDao;

    @Before
    public void setUp(){

        location = new Location();
        location.setAddress("falls church");
        location.setEmail("1093599417@qq.com");
        location.setName("Feixiong");
        location.setPhone_number("202-718-7348");
        location.setSeller_id(1L);
        locationDao.save(location);
        product = new Product();
        product.setName("Light");
        product.setDescription("very good");
        product.setLocation(location);
        productDao.save(product);
    }
    @After
    public void tearDown(){
        if(product != null){
            Assert.assertTrue(productDao.delete(product.getName()));
        }
    }

//    List<Product> getProduct();
//    Product save(Product product);
//    Boolean delete(String proName);
//    Product getProductById(Long ProductId);
    @Test
    public void getProductTest(){
        List<Product> products = productDao.getProduct();
        int ExpectedNumberOfProduct = 5;
        Assert.assertEquals(products.size(),ExpectedNumberOfProduct);
    }

    @Test
    public void saveTest(){
        assert(0 != product.getId());
    }

}
