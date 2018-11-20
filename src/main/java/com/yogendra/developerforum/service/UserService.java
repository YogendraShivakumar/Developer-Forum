package com.yogendra.developerforum.service;

import com.yogendra.developerforum.beans.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    /**
     * Create the user with requested parameter
     * @param user
     * @return
     */
    User createUser(User user);

    /**
     * list the users from the database
     * @return
     */
    List<User> getUsers();
}
