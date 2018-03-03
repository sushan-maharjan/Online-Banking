package com.sushan.onlinebank.com.sushan.onlinebank.DAO;

import com.sushan.onlinebank.com.sushan.onlinebank.domain.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by trauma_sushan on 2/27/2018.
 */
public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long> {
    PrimaryAccount findByAccountNumber(int accountNumber);
}
