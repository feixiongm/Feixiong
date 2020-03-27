package com.ascending.controller;

import com.ascending.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(value = {"/files"})
public class FileController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FileService fileService;

    @RequestMapping(value = {""}, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file") MultipartFile mf) {
        logger.info(mf.getName());
        File temp = new File("/Users/mengfeixiong/Downloads/credentials.csv ");
        try {
            mf.transferTo(temp);
            fileService.uploadObject(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}