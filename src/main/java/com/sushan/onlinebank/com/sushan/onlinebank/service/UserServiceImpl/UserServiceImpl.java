package com.sushan.onlinebank.com.sushan.onlinebank.service.UserServiceImpl;

import com.sushan.onlinebank.com.sushan.onlinebank.DAO.RoleDao;
import com.sushan.onlinebank.com.sushan.onlinebank.DAO.UserDAO;
import com.sushan.onlinebank.com.sushan.onlinebank.domain.User;
import com.sushan.onlinebank.com.sushan.onlinebank.domain.security.UserRole;
import com.sushan.onlinebank.com.sushan.onlinebank.service.AccountService;
import com.sushan.onlinebank.com.sushan.onlinebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by trauma_sushan on 2/26/2018.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    AccountService accountService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public void save(User user) {
        userDao.save(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public boolean checkUserExists(String username, String email){
        if (checkUsernameExists(username) || checkEmailExists(username)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUsernameExists(String username) {
        if (null != findByUsername(username)) {
            return true;
        }

        return false;
    }

    public boolean checkEmailExists(String email) {
        if (null != findByEmail(email)) {
            return true;
        }

        return false;
    }

    public User saveUser (User user) {
        return userDao.save(user);
    }

    public User createUser(User user, Set<UserRole> userRoles){
       String password = passwordEncoder.encode(user.getPassword());
       user.setPassword(password);
       for(UserRole ur : userRoles ){
           roleDao.save(ur.getRole());
       }
       user.getUserRoles().addAll(userRoles);
        user.setPrimaryAccount(accountService.createPrimaryAccount());
        user.setSavingsAccount(accountService.createSavingsAccount());
        User user1 = userDao.save(user);
        return user1;
    }
}
