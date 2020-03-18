package com.ascending.controller;

import com.ascending.model.User;
import com.ascending.service.JWTService;
import com.ascending.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = {"/auth"})
public class AuthenticationController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;
    private String errorMsg = "The email or password is not correct.";
    private String tokenKeyWord = "Authorization";
    private String tokenType = "Bearer";

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Map> authentication(@RequestBody User user){
        //1. validate user
        //2. generate Token
        String token = "";
        Map<String,String> result = new HashMap<>();
        try{
            logger.debug(user.toString());
            User u = userService.getUserByCredentials(user.getEmail(),user.getPassword());
            result.put("msg",errorMsg);
            if(u == null) return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(result);
            logger.debug(u.toString());
            token = jwtService.generateToken(u);
            result.put("token",token);

        }
        catch (Exception e){
            String msg = e.getMessage();
            if(msg == null) msg = "BAD REQUEST";
            logger.error(msg);
            result.put("msg",msg);
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(result);

        }
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(result);
    }

}
