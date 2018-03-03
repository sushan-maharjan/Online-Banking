package com.sushan.onlinebank.com.sushan.onlinebank.DAO;

import com.sushan.onlinebank.com.sushan.onlinebank.domain.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by trauma_sushan on 2/27/2018.
 */
public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {
    SavingsAccount findByAccountNumber(int accountNumber);
}
