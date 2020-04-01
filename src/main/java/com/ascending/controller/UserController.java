package com.ascending.controller;

import com.ascending.model.Role;
import com.ascending.model.User;
import com.ascending.repository.RoleDaoImpl;
import com.ascending.service.RoleService;
import com.ascending.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User createUser(@RequestBody User user) {
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleService.getRoleById(3L));
        user.setRoles(roleList);
        userService.save(user);
        //if (u == null) logger.error("The user was not saved.");
        return user;
    }

}
