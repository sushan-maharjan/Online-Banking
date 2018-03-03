package com.sushan.onlinebank.com.sushan.onlinebank.DAO;

import com.sushan.onlinebank.com.sushan.onlinebank.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by trauma_sushan on 2/26/2018.
 */
public interface RoleDao extends CrudRepository<Role, Integer> {
    public Role findByName(String name);
}
