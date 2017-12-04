package com.netcracker.developerforum.service;

import com.netcracker.developerforum.beans.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(User user);
}
