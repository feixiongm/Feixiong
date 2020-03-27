package com.ascending.init;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.ascending.service.FileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class AWSConfig {
    @Bean
    public FileService getFileService() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();
        FileService fileService = new FileService(s3Client);
        fileService.setBucketName("feixiong-11");
        return fileService;
    }
}
