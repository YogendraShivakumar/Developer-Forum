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
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

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
        User user = mongoOperation.findOne(new Query(Criteria.where("email").is(username)), User.class, "user");
        return user;
    }

    @Override
    public void deleteUser(String userName) {
        try {
            User user = mongoOperation.findAndRemove(new Query(Criteria.where("email").is(userName)), User.class, "user");
            Login login = mongoOperation.findAndRemove(new Query(Criteria.where("userName").is(userName)), Login.class, "login");

            if (user == null || login == null) {
                throw new RuntimeException("Problem occurred while deleting User " + userName + ". User may Doesn't exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public User updateUser(String userName, User user) {
        BasicDBObject object = new BasicDBObject();
        String[] array = new String[]{};
        if (user.getEmail() != null && !StringUtils.isEmpty(user.getEmail())) {
            throw new RuntimeException("You cannot update Email as it is used as username");
        }
        if (user.getFirstName() != null && !StringUtils.isEmpty(user.getFirstName())) {
            object.put("firstName", user.getFirstName());
        }
        if (user.getLastName() != null && !StringUtils.isEmpty(user.getLastName())) {
            object.put("lastName", user.getLastName());
        }
        if (user.getMobileNumber() != null && !StringUtils.isEmpty(user.getMobileNumber())) {
            object.put("mobileNumber", user.getMobileNumber());
        }
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(false);
        User modifiedUser = mongoOperation.findAndModify(new Query(Criteria.where("email").is(userName)),
                Update.fromDBObject(object, array), options, User.class, "user");
        return modifiedUser;
    }

    @Override
    public void updatePassword(String userName, String password) {
        User user = getUser(userName);
        Update update = new Update();
        if (user != null) {
            mongoOperation.findAndModify(new Query(Criteria.where("userName").is(userName)),
                    update.set("password", password), Login.class, "login");
        } else {
            throw new RuntimeException("User Doesn't Exists");
        }
    }
}