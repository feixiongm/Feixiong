package com.ascending.service;

import com.ascending.repository.SellerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    private SellerDao sellerDao;
}
