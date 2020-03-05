package com.ascending.repository;

import com.ascending.model.Seller;

import java.util.List;

public interface SellerDao {
    List<Seller> getSellers();
    Seller save(Seller seller);
    Boolean delete(String sellerName);
    Seller getSellerById(Long SellerId);
}
