package com.ascending.controller;

import com.ascending.model.Image;
import com.ascending.model.User;
import com.ascending.service.FileService;
import com.ascending.service.ImageService;
import com.ascending.service.JWTService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping(value = {"/files"})
public class FileController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FileService fileService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //return String url,json map
    public void uploadFile(@RequestParam("file") MultipartFile mf, ServletRequest request) {
        logger.info(mf.getName());
        //hardcode convert to input mf
        //File path on system
        //get linux system path
        String key = fileService.uploadObject(mf);
        Image image = new Image();
        image.setFileName(mf.getOriginalFilename());
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("user");
        image.setUser(user);
        image.setUrl(fileService.getObjectUrl(key).toExternalForm());
        imageService.save(image);
    }

    //placeholder , produce format
//    @RequestMapping(value = {""}, method = RequestMethod.GET, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String getUrl(@PathVariable String key){
//        //check null check empty
//        //not able to find key throw exception
//        return fileService.getObjectUrl(key);
//    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Image createProduct(@RequestBody Image image) {
        logger.debug("Image:" + image.toString());
        return imageService.save(image);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getPublicUrl(@RequestParam("s3Key") String key) {
        String msg = String.format("Invalid parameters key=%s.", key);
        if (key == null) return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(msg);

        try {
            logger.info(String.format(">>>>>>>>>>>>>>>>>Getting url of %s ...", key));
            URL url = fileService.getObjectUrl(key);
            msg = String.format("The url of the file with key=%s is: %s.",
                    key, url);
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(msg);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpServletResponse.SC_NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}