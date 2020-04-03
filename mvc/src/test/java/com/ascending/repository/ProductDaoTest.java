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
    private LocationDao locationDao;
    @Autowired
    private ProductDao productDao;

    @Before
    public void setUp() {
        location = locationDao.getLocationById(1L);

        product = new Product();
        product.setName("Light");
        product.setDescription("very good");
        product.setLocation(location);
        productDao.save(product);
    }

    @After
    public void tearDown() {
        if (product != null) {
            Assert.assertTrue(productDao.deleteByname(product.getName()));
        }
    }

    @Test
    public void getProductTest() {
        List<Product> products = productDao.getProduct();
        int ExpectedNumberOfProduct = 5;
        Assert.assertEquals(products.size(), ExpectedNumberOfProduct);
    }

    @Test
    public void saveTest() {
        assert (0 != product.getId());
    }

    @Test
    public void updateProductTest() {
        product.setName("hello");
        productDao.save(product);
        Assert.assertEquals(product.getName(), "hello");
    }

    @Test
    public void deleteProductTest() {
        boolean isSuccess = productDao.deleteByname(product.getName());
        Assert.assertTrue(isSuccess);
    }

    @Test
    public void getProductByIdTest() {
        String testName = "product_name1";
        Product product = productDao.getProductById(1L);
        Assert.assertEquals(product.getName(),testName);
    }
}
