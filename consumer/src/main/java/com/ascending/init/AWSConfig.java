package com.ascending.init;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class AWSConfig {

    @Bean
    public AmazonSQS getAmazonSQS() {
        return AmazonSQSClientBuilder.standard().
                withCredentials(new DefaultAWSCredentialsProviderChain()).build();
    }
}
