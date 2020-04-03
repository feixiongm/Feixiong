package com.ascending.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.net.URL;
import java.util.UUID;

//@Service
public class FileService {
    private String bucketName;
    private String bucket;
    private AmazonS3 s3Client;
    private Logger logger = LoggerFactory.getLogger(getClass());


    public FileService() {
        s3Client = AmazonS3ClientBuilder.standard()
                .withRegion("us-east-1")
                .build();
    }
    public FileService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
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



    //@Value("aws.region")
    //private String region
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void uploadObject(File f, String bucketName) {
//        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                .withRegion("us-east-1")
//                .build();
        try {
            if (s3Client.doesObjectExist(bucketName, f.getName())) {
                logger.info(String.format("The file '%s' exists in the bucket", f.getName()));
            }

            String uuid = UUID.randomUUID().toString();
            String originalFileName = f.getName();
            String newFileName = Files.getNameWithoutExtension(originalFileName) + uuid + Files.getFileExtension(originalFileName);
            s3Client.putObject(bucketName, f.getName(), f);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getObjectUrl(String key) {

        try {
            URL url = s3Client.getUrl(bucketName, key);
            return url == null ? null : url.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void createFolder() {

    }
}
