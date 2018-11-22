package com.yogendra.developerforum.rest.controller;

import com.yogendra.developerforum.beans.Login;
import com.yogendra.developerforum.beans.User;
import com.yogendra.developerforum.service.UserService;
import com.yogendra.developerforum.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yogs0616 on 11/28/2017.
 */
@RestController
@EnableAutoConfiguration
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    UserService userService = new UserServiceImpl();

    @RequestMapping(value = "/createUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestParam String userName, @RequestBody User user) {
        return userService.updateUser(userName, user);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@RequestParam String userName) {
        userService.deleteUser(userName);
    }

    @RequestMapping(value = "/users/getUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@RequestParam String userName) {
        User user = userService.getUser(userName);
        if (user == null) {
            throw new RuntimeException("User Doesn't Exists Please check the username");
        }
        return user;
    }

    @RequestMapping(value = "users/updatePassword", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updatePassword(String username, String password) {
        userService.updatePassword(username, password);
    }
}
