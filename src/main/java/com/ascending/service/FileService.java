package com.ascending.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;

//@Service
public class FileService {
    private String bucketName;
    private String bucket;
    private AmazonS3 s3Client;


    public FileService() {
         s3Client = AmazonS3ClientBuilder.standard()
                .withRegion("us-east-1")
                .build();
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getBucket() {
        return bucket;
    }

    public AmazonS3 getS3Client() {
        return s3Client;
    }

    public FileService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    //@Value("aws.region")
    //private String region
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void uploadObject(File f){
//        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                .withRegion("us-east-1")
//                .build();
        s3Client.putObject(bucketName, f.getName(), f);
    }

    public String getObjectUrl(String key){

        try {
            URL url = s3Client.getUrl(bucketName, key);
            return url == null ? null : url.toString();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public void createFolder(){

    }
}
