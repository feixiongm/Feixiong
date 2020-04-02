package com.ascending.controller;

import com.ascending.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(value = {"/files"})
public class FileController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "{}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file") MultipartFile mf) {
        logger.info(mf.getName());
        File temp = new File("/credentials.csv");
        try {

            mf.transferTo(temp);
            fileService.uploadObject(temp);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    //placeholder , produce format
//    @RequestMapping(value = {""}, method = RequestMethod.GET, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String getUrl(@PathVariable String key){
//        //check null check empty
//        //not able to find key throw exception
//        return fileService.getObjectUrl(key);
//    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getPublicUrl(@PathVariable String key){
        String msg = String.format("Invalid parameters key=%s.", key);
        if(key == null) return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(msg);

        try {
            logger.info(String.format(">>>>>>>>>>>>>>>>>Getting url of %s ...", key));
            String url = fileService.getObjectUrl(key);
            msg = String.format("The url of the file with key=%s is: %s.",
                    key,url);
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(msg);
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpServletResponse.SC_NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}