package com.yogendra.developerforum.service.impl;

import com.mongodb.BasicDBObject;
import com.yogendra.developerforum.beans.Login;
import com.yogendra.developerforum.beans.User;
import com.yogendra.developerforum.configuration.SpringMongoConfig;
import com.yogendra.developerforum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;
import sun.rmi.runtime.Log;

import java.lang.reflect.Field;
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
        login.setUserName(user.getEmail());
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

    @Override
    public User getUser(String username) {
        User user = (User) mongoOperation.findOne(new Query(Criteria.where("email").is(username)), User.class, "user");
        return user;
    }

    @Override
    public void deleteUser(String userName) {
        try {
            mongoOperation.findAndRemove(new Query(Criteria.where("email").is(userName)), User.class, "user");
            mongoOperation.findAndRemove(new Query(Criteria.where("userName").is(userName)), Login.class, "login");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public User updateUser(String userName, User user) {
        BasicDBObject object = new BasicDBObject();
        List<String> arr = new ArrayList<>();
        arr.add("email");
        if (user.getEmail() != null && !StringUtils.isEmpty(user.getEmail())) {
            throw new RuntimeException("You cannot update Email as it is used as username");
        }

        if (user.getFirstName() != null && !StringUtils.isEmpty(user.getFirstName())) {
            object.put("firstName", user.getFirstName());
        } else {
            arr.add("firstName");
        }
        if (user.getLastName() != null && !StringUtils.isEmpty(user.getLastName())) {
            object.put("lastName", user.getLastName());
        } else {
            arr.add("lastName");
        }
        if (user.getMobileNumber() != null && !StringUtils.isEmpty(user.getMobileNumber())) {
            object.put("mobileNumber", user.getMobileNumber());
        } else {
            arr.add("mobileNumber");
        }

        User modifiedUser = mongoOperation.findAndModify(new Query(Criteria.where("email").is(userName)), Update.fromDBObject(object, arr.toArray().toString()), User.class, "user");
        return modifiedUser;
    }
}