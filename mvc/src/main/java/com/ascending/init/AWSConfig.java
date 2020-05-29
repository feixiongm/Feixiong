package com.ascending.init;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.ascending.service.FileService;
import com.ascending.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class AWSConfig {
    @Bean
    public AmazonS3 getAmazonS3() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();
        return s3Client;
    }

    @Bean
    public FileService getFileService() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();
        FileService fileService = new FileService(s3Client);
//        fileService.setBucketName("feixiong-11");
        System.out.println("dev");
        return fileService;
    }
//    @Bean
//    public MessageService getMessageService(){
//        AmazonSQS amazonSQS = AmazonSQSClientBuilder.standard().build();
//        MessageService messageService = new MessageService(amazonSQS);
//        return messageService;
//    }

    @Bean
    public AmazonSQS getAmazonSQS() {

        return AmazonSQSClientBuilder.standard().
                withCredentials(new DefaultAWSCredentialsProviderChain()).build();
    }



}
