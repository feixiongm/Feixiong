package com.ascending.repository;

import com.ascending.model.Image;

public interface ImageDao {

    Image save(Image image);

    Boolean deleteByFileName(String fileName);
}
