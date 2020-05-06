package com.ascending.repository;

import com.ascending.init.ApplicationBootstrap;
import com.ascending.model.Image;
import com.ascending.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class ImageDaoTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ImageDao imageDao;
    private Image image;

    @Before
    public void setUp() {
        image = new Image();
        User user = userDao.findUserById(1L);
        image.setUser(user);
        image.setFileName("123");
        imageDao.save(image);
    }

    @Test
    public void save() {

        assert (0 != image.getId());
    }

    @After
    public void tearDown(){
        if(image != null)
            imageDao.deleteByFileName(image.getFileName());
    }
}
