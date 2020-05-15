package com.ascending.init;

import com.amazonaws.services.sqs.model.Message;
import com.ascending.service.MessageService;
import com.ascending.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.ascending"})
public class ConsumerApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext app = SpringApplication.run(ConsumerApplication.class,args);
        MessageService messageService = app.getBean(MessageService.class);
        ProcessService processService = app.getBean(ProcessService.class);
       try {
           List<Message> messageList = messageService.getMessages("Feixiong");
           for (Message m : messageList) {
               processService.processMessage(m.getBody());
               System.out.println(m.getBody());
           }
       }catch(IOException e){
           e.printStackTrace();
       }
    }
}
