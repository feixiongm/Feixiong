package com.ascending.Service;

import com.ascending.init.ApplicationBootstrap;
import com.ascending.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class MessageServiceTest {
    @Autowired
    MessageService messageService;
    @Test
    public void sendMessage(){
        messageService.sendMessage("abc");
    }
}
