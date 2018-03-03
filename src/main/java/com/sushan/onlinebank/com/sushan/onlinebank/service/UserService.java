package com.sushan.onlinebank.com.sushan.onlinebank.service;

import com.sushan.onlinebank.com.sushan.onlinebank.domain.User;
import com.sushan.onlinebank.com.sushan.onlinebank.domain.security.UserRole;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by trauma_sushan on 2/26/2018.
 */

public interface UserService {
    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);

    void save (User user);

    public User createUser(User user, Set<UserRole> userRoles);

    User findByUsername(String username);

    User findByEmail(String email);


}
