package com.ascending.service;

import com.ascending.model.Image;
import com.ascending.repository.ImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    ImageDao imageDao;

    public Image save(Image image) {
        return imageDao.save(image);
    }

    public Boolean deleteByFileName(String fileName) {
        return imageDao.deleteByFileName(fileName);
    }
}
