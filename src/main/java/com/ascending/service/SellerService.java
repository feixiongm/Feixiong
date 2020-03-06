package com.ascending.service;

import com.ascending.model.Seller;
import com.ascending.repository.SellerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerDao sellerDao;

//    List<Seller> getSellers();
//    Seller save(Seller seller);
//    Boolean delete(String sellerName);
//    Seller getSellerById(Long sellerId);

    public List<Seller> getSellers(){return sellerDao.getSellers();}
    public Seller save(Seller seller){return sellerDao.save(seller);}
    public Seller update(Seller seller){return sellerDao.save(seller);}
    public Boolean delete(String sellerName){return sellerDao.delete(sellerName);}
    public Seller getSellerById(Long sellerId){return sellerDao.getSellerById(sellerId);}
}
