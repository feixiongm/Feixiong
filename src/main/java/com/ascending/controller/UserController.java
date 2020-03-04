package com.ascending.controller;

import com.ascending.model.User;
import com.ascending.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {

    private UserService userService = new UserService();

    public UserController() {
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
