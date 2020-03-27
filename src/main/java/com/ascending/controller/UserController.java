package com.ascending.controller;

import com.ascending.model.User;
import com.ascending.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {""}, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User createUser(@RequestBody User user) {
        User u = userService.save(user);
        //add role
        if (u!=null) logger.error("The location was not saved.");
        return u;
    }
}
