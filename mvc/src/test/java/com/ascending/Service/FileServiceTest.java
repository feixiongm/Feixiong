package com.ascending.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.ascending.init.ApplicationBootstrap;
import com.ascending.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class FileServiceTest {
    @Autowired
    private FileService fileService;
    File file = new File("/Users/mengfeixiong/Downloads/credentials.csv");
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String buckeName = "feixiong-11";



    @Test
    public void uploadObjectTest(){
//         AmazonS3 s3Fake = mock(AmazonS3.class);
//        fileService.setBucketName("feixiong-11");

        fileService.uploadObject(file,"feixiong-11");
        Mockito.verify(fileService.getS3Client(),times(1)).
                putObject(eq(fileService.getBucketName()),eq(file.getName()),anyString());
        //assertNotNull(fileService.getObjectUrl(file.getName()));
    }
//    @Test
//    public void getObjectUrlTest(){
//        Mockito.verify(fileService.getS3Client(),times(1)).getUrl(eq())
//    }
}
