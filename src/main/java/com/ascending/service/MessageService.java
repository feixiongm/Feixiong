package com.ascending.service;

import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
//    @Autowired
//    private AmazonSQS amazonSQS;
private String myQueueUrl = "https://sqs.us-east-1.amazonaws.com/617060188729/Feixiong";
    public void sendMessage(String msg){
        AmazonSQS amazonSQS = AmazonSQSClientBuilder.standard().build();
        // Send a message
        final SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.withQueueUrl(myQueueUrl).withMessageBody(msg);
// When you send messages to a FIFO queue, you must provide a non-empty MessageGroupId.
        //sendMessageRequest.setMessageGroupId("messageGroup1");

// Uncomment the following to provide the MessageDeduplicationId
//sendMessageRequest.setMessageDeduplicationId("1");
        final SendMessageResult sendMessageResult = amazonSQS.sendMessage(sendMessageRequest);
        final String sequenceNumber = sendMessageResult.getSequenceNumber();
        final String messageId = sendMessageResult.getMessageId();
        System.out.println("SendMessage succeed with messageId " + messageId + ", sequence number " + sequenceNumber + "\n");
    }



}
