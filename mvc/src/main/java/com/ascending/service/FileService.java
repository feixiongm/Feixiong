package com.ascending.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.util.UUID;

@Service
public class FileService {
    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Autowired
    private AmazonS3 s3Client;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public FileService(AmazonS3 s3Client) {
        System.out.println("service");
        this.s3Client = s3Client;
    }

    public String getBucketName() {
        return bucketName;
    }

    public AmazonS3 getS3Client() {
        return s3Client;
    }

    public String uploadObject(MultipartFile mf) {

        try {
            String uuid = UUID.randomUUID().toString();
            String originalFileName = mf.getOriginalFilename();
            String newFileName = Files.getNameWithoutExtension(originalFileName) + uuid + Files.getFileExtension(originalFileName);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(mf.getContentType());
            objectMetadata.setContentLength(mf.getSize());
            PutObjectRequest request = new PutObjectRequest(bucketName, newFileName, mf.getInputStream(),objectMetadata);
            s3Client.putObject(request);
            return newFileName;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public URL getObjectUrl(String key) {
        return s3Client.getUrl(bucketName, key);
//        try {
//            URL url = s3Client.getUrl(bucketName, key);
//            return url == null ? null : url.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

    }

    public void createFolder() {

    }
}
