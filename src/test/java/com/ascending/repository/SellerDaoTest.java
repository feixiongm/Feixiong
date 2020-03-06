package com.ascending.repository;


import com.ascending.init.ApplicationBootstrap;
import com.ascending.model.Seller;
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
public class SellerDaoTest {

    private Seller seller;
    @Autowired
    private SellerDao sellerDao;

    @Before
    public void setUp(){
        seller = new Seller();
        seller.setName("xiong");
        seller.setEmail("123@qq.com");
        seller.setPhone_number("12345678");
        sellerDao.save(seller);
    }
    @After
    public void tearDown(){
        if(seller != null){
            Assert.assertTrue(sellerDao.delete(seller.getName()));
        }
    }

//    List<Seller> getSellers();
//    Seller save(Seller seller);
//    Boolean delete(String sellerName);
//    Seller getSellerById(Long SellerId);

    @Test
    public void getSellerTest(){
        int ExpectedNumberOFSeller = 5;
        List<Seller> sellers = sellerDao.getSellers();
        Assert.assertEquals(sellers.size(),ExpectedNumberOFSeller);
    }

    @Test
    public void saveTest(){
        assert(0 != seller.getId());
    }

    @Test
    public void deleteTest(){
        boolean isSuccess = sellerDao.delete(seller.getName());
        Assert.assertTrue(isSuccess);
    }

    @Test
    public void getSellerById(){
        String testName = "seller1";
        Seller seller= sellerDao.getSellerById(1L);
        Assert.assertEquals(seller.getName(), testName);
    }
}
