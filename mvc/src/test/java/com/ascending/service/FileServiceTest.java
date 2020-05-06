package com.ascending.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.ascending.init.ApplicationBootstrap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class FileServiceTest {
    @Autowired
    private FileService fileService;
    //File file = new File("/credentials.csv");
    private Logger logger = LoggerFactory.getLogger(getClass());
    //TODO environment variable
    //private String buckeName = "feixiong-11";
    @Autowired
    private AmazonS3 amazonS3;
    MultipartFile mf = mock(MultipartFile.class);

    @Before
    public void setUp(){

        InputStream anyInputStream = new ByteArrayInputStream("test data".getBytes());
        try {
            when(mf.getInputStream()).thenReturn(anyInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        when(mf.getOriginalFilename()).thenReturn("123");
//        try {
//            when(amazonS3.getUrl(anyString(), anyString())).thenReturn(new URL("http", "ascending.com", 800, "sdf.pdf"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
    }


    @Test
    public void uploadObjectTest() throws IOException {

        fileService.uploadObject(mf);
        Mockito.verify(amazonS3,times(1)).
                putObject(any(PutObjectRequest.class));
    }
    @Test
    //todo add test
    public void getObjectUrlTest(){
        try {
            when(amazonS3.getUrl(anyString(), anyString())).thenReturn(new URL("http", "ascending.com", 800, "sdf.pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        URL url = fileService.getObjectUrl("123");

        Mockito.verify(fileService.getS3Client(),times(1)).getUrl(anyString(),anyString());
    }


}
