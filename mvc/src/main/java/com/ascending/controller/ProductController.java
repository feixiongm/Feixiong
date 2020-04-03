package com.ascending.controller;

import com.ascending.jsonView.View;
import com.ascending.model.Product;
import com.ascending.service.ProductService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/products"})
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProductService productService;
    @JsonView(View.Product.class)
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Product> getProduct() {
        return productService.getProducts();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Product getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return product;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Product createProduct(@RequestBody Product product) {
        logger.debug("Product:" + product.toString());
        return productService.save(product);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Product updateProduct(@RequestBody Product product) {
        logger.debug("Product:" + product.toString());
        Product updatePro = productService.update(product);
        if (updatePro != null) logger.error("Product is not update");
        return updatePro;
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public String deleteProduct(@RequestParam("productName") String proName) {
        logger.debug("Product:" + proName);
        String msg = "The product was deleted";
        boolean isSuccess = productService.deleteByName(proName);
        if (!isSuccess) msg = "The product was not deleted";
        return msg;
    }


}
