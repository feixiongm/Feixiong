package com.ascending.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class MessageListener implements javax.jms.MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("***************Inside the receive message queue listener***************");
            System.out.println("Received: " + ((TextMessage) message).getText());
        } catch (JMSException e){
            e.printStackTrace();
        }
    }
}
