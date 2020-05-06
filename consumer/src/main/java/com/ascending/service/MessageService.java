package com.ascending.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private AmazonSQS amazonSQS;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public String getQueueUrl(String queueName) {
        GetQueueUrlResult getQueueUrlResult = amazonSQS.getQueueUrl(queueName);
        logger.info("QueueUrl:" + getQueueUrlResult.getQueueUrl());
        return getQueueUrlResult.getQueueUrl();
    }

    public List<Message> getMessages(String queueName) {
        String myQueueURL = getQueueUrl(queueName);
        List<Message> messages = amazonSQS.receiveMessage(myQueueURL).getMessages();
        logger.info("The message is " + messages);
        return messages;
    }

}
