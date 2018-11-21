package com.yogendra.developerforum.service;

import com.yogendra.developerforum.beans.Login;
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

    /**
     * Retrieve the user with specified Username
     * @param userName
     * @return
     */
    User getUser(String userName);

    /**
     * Delete the user with specified User Name
     * @param userName
     */
    void deleteUser(String userName);

    /**
     * Update the user's information with specified
     * @param userName
     * @return
     */
    User updateUser(String userName, User user);
}
