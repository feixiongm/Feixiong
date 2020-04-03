package com.ascending.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.ascending.init.ApplicationBootstrap;
import com.ascending.service.MessageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class MessageServiceMockSQSTest {
    @Autowired
    private MessageService messageService;
    @Autowired
    private AmazonSQS amazonSQS;
    private String fakeQueueUrl = "www.fakeQueueUrl.com";

    @Before
    public void setUp() {
        GetQueueUrlResult urlResult = Mockito.mock(GetQueueUrlResult.class);
        Mockito.when(urlResult.getQueueUrl()).thenReturn(fakeQueueUrl);
        Mockito.when(amazonSQS.getQueueUrl(anyString())).thenReturn(urlResult);
    }

    @Test
    public void sendMessageTest() {
        messageService.sendMessage("hello", "Feixiong");
        Mockito.verify(messageService.getAmazonSQS(), Mockito.times(1))
                .sendMessage(any());
    }
@After
    public void tearDown(){
       clearInvocations();
}
}
