package com.yogendra.developerforum.service.impl;

import com.yogendra.developerforum.beans.Login;
import com.yogendra.developerforum.beans.User;
import com.yogendra.developerforum.configuration.SpringMongoConfig;
import com.yogendra.developerforum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.ArrayList;
import java.util.List;

import static com.yogendra.developerforum.constants.ForumConstants.ACTIVE;

public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    ApplicationContext ctx =
            new AnnotationConfigApplicationContext(SpringMongoConfig.class);
    MongoOperations mongoOperation =
            (MongoOperations) ctx.getBean("mongoTemplate");

    @Override
    public User createUser(User user) {
        User createdUser = new User();
        Login login = new Login();
        createdUser.setEmail(user.getEmail());
        createdUser.setFirstName(user.getFirstName());
        createdUser.setLastName(user.getLastName());
        createdUser.setMobileNumber(user.getMobileNumber());

        login.setPassword(user.getRegistration().getPassword());
        login.setUserName(user.getRegistration().getUserName());
        login.setUserState(ACTIVE);

        mongoOperation.insert(createdUser, "user");
        mongoOperation.insert(login, "login");
        return createdUser;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users = mongoOperation.findAll(User.class, "user");
        logger.debug("Users : {}", users);
        return users;
    }
}
