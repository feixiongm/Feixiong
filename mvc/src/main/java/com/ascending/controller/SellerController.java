package com.ascending.controller;

import com.ascending.jsonView.View;
import com.ascending.model.Seller;
import com.ascending.service.SellerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping(value = {"/sellers"})
public class SellerController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SellerService sellerService;

    @JsonView(View.Seller.class)
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Seller> getSeller() {
        return sellerService.getSellers();
    }

    @JsonView(View.Seller.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Seller getSellerById(@PathVariable Long id) {
        return sellerService.getSellerById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Seller createSeller(@RequestBody Seller seller) {
        logger.debug("Seller:" + seller);
        Seller result = sellerService.save(seller);
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Seller updateSeller(@RequestBody Seller seller) {
        logger.debug("Seller:" + seller);
        Seller updateSeller = sellerService.update(seller);
        return updateSeller;
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public String deleteSeller(@RequestParam("sellerName") String sellerName) {
        String msg = "The seller was deleted";
        boolean isSuccess = sellerService.delete(sellerName);
        if (!isSuccess) msg = "The seller was not deleted";
        return msg;
    }

}
