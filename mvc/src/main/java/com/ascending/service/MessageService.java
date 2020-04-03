package com.ascending.service;

import com.amazonaws.services.dynamodbv2.xspec.M;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {
    @Autowired
    private AmazonSQS amazonSQS;
    //private String myQueueUrl = "https://sqs.us-east-1.amazonaws.com/617060188729/Feixiong";
    private Logger logger = LoggerFactory.getLogger(getClass());


    public AmazonSQS getAmazonSQS() {
        return amazonSQS;
    }

    public String createQueue(String queueName) {
        String queueUrl = null;
        try {
            queueUrl = getQueueUrl(queueName);
        } catch (QueueDoesNotExistException e) {
            CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
            queueUrl = amazonSQS.createQueue(createQueueRequest).getQueueUrl();
        }
        logger.info(queueUrl);

        return queueUrl;
    }

    public String getQueueUrl(String queueName) {
        GetQueueUrlResult getQueueUrlResult = amazonSQS.getQueueUrl(queueName);
        logger.info("QueueUrl:" + getQueueUrlResult.getQueueUrl());
        return getQueueUrlResult.getQueueUrl();
    }

    public void sendMessage(String msg, String queueName) {
        // Send a message
        final SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.withQueueUrl(getQueueUrl(queueName)).withMessageBody(msg);
// When you send messages to a FIFO queue, you must provide a non-empty MessageGroupId.
        //sendMessageRequest.setMessageGroupId("messageGroup1");

// Uncomment the following to provide the MessageDeduplicationId
//sendMessageRequest.setMessageDeduplicationId("1");
        final SendMessageResult sendMessageResult = amazonSQS.sendMessage(sendMessageRequest);
        //final String sequenceNumber = sendMessageResult.getSequenceNumber();
        //final String messageId = sendMessageResult.getMessageId();
        System.out.println("SendMessage succeed with messageId " + "\n");
    }

    public List<Message> getMessages(String queueName) {
        String myQueueURL = getQueueUrl(queueName);
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueURL);
        List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
        logger.info("The message is " + messages);
        return messages;
    }

}
