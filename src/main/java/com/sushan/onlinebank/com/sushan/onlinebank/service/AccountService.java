package com.sushan.onlinebank.com.sushan.onlinebank.service;

import com.sushan.onlinebank.com.sushan.onlinebank.domain.PrimaryAccount;
import com.sushan.onlinebank.com.sushan.onlinebank.domain.SavingsAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * Created by trauma_sushan on 2/27/2018.
 */

public interface AccountService{
    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
    public void deposit(String accountType, double amount, Principal principal);
    public void withdraw(String accountType, double amount, Principal principal);
}
