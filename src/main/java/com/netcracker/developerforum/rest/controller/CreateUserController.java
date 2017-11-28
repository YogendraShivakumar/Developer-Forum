package com.netcracker.developerforum.rest.controller;

import com.netcracker.developerforum.beans.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yogs0616 on 11/28/2017.
 */
@RestController
@EnableAutoConfiguration
public class CreateUserController {
    @RequestMapping(value = "/registration",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user){
        return user;
    }
}
