package com.ascending.Service;

import com.ascending.init.ApplicationBootstrap;
import com.ascending.model.Location;
import com.ascending.model.Product;
import com.ascending.service.LocationService;
import com.ascending.service.ProductService;
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
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private LocationService locationService;
    private Product product;
    private Location location;

    @Before
    public void setUp(){
        location = locationService.getLocationById(1L);

        product = new Product();
        product.setName("Light");
        product.setDescription("very good");
        product.setLocation(location);
        productService.save(product);
    }

    @After
    public void tearDown(){
        if(product != null)
            productService.deleteByName(product.getName());
    }

    @Test
    public void saveTest(){
        Assert.assertTrue(0 != productService.save(product).getId());
    }

    @Test
    public void updateTest(){
        product.setName("hello");
        productService.update(product);
        Assert.assertEquals(product.getName(),"hello");
    }

    @Test
    public void deleteByNameTest(){
        boolean isSuccess = productService.deleteByName(product.getName());
        Assert.assertTrue(isSuccess);
    }

    @Test
    public void getProductByIdTest() {
        String testName = "product_name1";
        Product product = productService.getProductById(1L);
        Assert.assertEquals(product.getName(),testName);
    }

    @Test
    public void getProductTest() {
        List<Product> products = productService.getProducts();
        int ExpectedNumberOfProduct = 5;
        Assert.assertEquals(products.size(), ExpectedNumberOfProduct);
    }
}
