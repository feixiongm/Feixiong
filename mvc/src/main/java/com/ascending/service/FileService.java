package com.ascending.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.util.UUID;

//@Service
public class FileService {
    private String bucketName;
    private String bucket;
    private AmazonS3 s3Client;
    private Logger logger = LoggerFactory.getLogger(getClass());


   // public FileService() {
     //   s3Client = AmazonS3ClientBuilder.standard()
       //         .withRegion("us-east-1")
         //       .build();
   // }
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

    public void uploadObject(MultipartFile mf, String bucketName) {
//        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                .withRegion("us-east-1")
//                .build();
        try {
           // mf.getInputStream()
         //   if (s3Client.doesObjectExist(bucketName, f.getName())) {
           //     logger.info(String.format("The file '%s' exists in the bucket", f.getName()));
         //   }

            String uuid = UUID.randomUUID().toString();
            String originalFileName = mf.getOriginalFilename();
            String newFileName = Files.getNameWithoutExtension(originalFileName) + uuid + Files.getFileExtension(originalFileName);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(mf.getContentType());
            objectMetadata.setContentLength(mf.getSize());
            s3Client.putObject(bucketName, newFileName, mf.getInputStream(),objectMetadata);

        }catch(Exception e){
            e.printStackTrace();
            //logger
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
