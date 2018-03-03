package com.sushan.onlinebank.com.sushan.onlinebank.DAO;

import com.sushan.onlinebank.com.sushan.onlinebank.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by trauma_sushan on 2/26/2018.
 */
public interface UserDAO extends CrudRepository<User, Long>{
    public User findByUsername(String username);
    public User findByEmail(String email);
}
