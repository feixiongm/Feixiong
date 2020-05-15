package com.ascending.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProcessService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SendGridEmailService sendGridEmailService;

    @JmsListener(destination = "Feixiong")
    public void processMessage(String msg) throws IOException {
        logger.debug("Processing Message: " + msg);

        Map<String, String> fakeRequest = new HashMap<>();
        fakeRequest.put("username", "feixiongm");
        fakeRequest.put("avatar", "AVATAR_URL");
        fakeRequest.put("attached_text", "Sincerely.");
        fakeRequest.put("request_link", "REQUEST_LINK");

        Map<String, Object> fakeMessage = new HashMap<>();
        fakeMessage.put("subject", "Friend Invitation");
        fakeMessage.put("from", "feixiong0715@gmail.com");
        fakeMessage.put("to_emails", Arrays.asList("feixiongm@gwu.edu"));
        fakeMessage.put("to_usernames", Arrays.asList("Feixiong Meng"));
        fakeMessage.put("request", fakeRequest);

        String fakeMessageJson = new ObjectMapper().writeValueAsString(fakeMessage);

        sendGridEmailService.sendEmail(fakeMessageJson);
    }
}
