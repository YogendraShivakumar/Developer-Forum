package com.netcracker.developerforum.service.impl;

import com.netcracker.developerforum.beans.User;
import com.netcracker.developerforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

public class UserServiceImpl implements UserService{

    @Autowired
    MongoOperations mongoTemplate;

    @Override
    public User createUser(User user) {
        mongoTemplate.save(user);
        return user;
    }
}
