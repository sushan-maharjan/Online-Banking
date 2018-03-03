package com.sushan.onlinebank.com.sushan.onlinebank.service.UserServiceImpl;

import com.sushan.onlinebank.com.sushan.onlinebank.DAO.PrimaryAccountDao;
import com.sushan.onlinebank.com.sushan.onlinebank.DAO.SavingsAccountDao;
import com.sushan.onlinebank.com.sushan.onlinebank.domain.*;
import com.sushan.onlinebank.com.sushan.onlinebank.service.AccountService;
import com.sushan.onlinebank.com.sushan.onlinebank.service.TransactionService;
import com.sushan.onlinebank.com.sushan.onlinebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by trauma_sushan on 2/27/2018.
 */
@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    PrimaryAccountDao primaryAccountDao;
    @Autowired
    SavingsAccountDao savingsAccountDao;
    @Autowired
    UserService userService;
    @Autowired
    TransactionService transactionService;

    private static int nextAccountNumber = 1;

    @Override
    public PrimaryAccount createPrimaryAccount() {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.setAccountNumber(accountgen());
        primaryAccountDao.save(primaryAccount);
        return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());

    }

    @Override
    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(new BigDecimal(0.0));
        savingsAccount.setAccountNumber(accountgen());
        savingsAccountDao.save(savingsAccount);
        return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
    }

    private int accountgen(){
        return ++nextAccountNumber;
    }

    public void deposit(String accountType, double amount, Principal principal){
        User user = userService.findByUsername(principal.getName());

        if(accountType.equalsIgnoreCase("Primary")){
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(new Date(), "Deposit to Primary Account",
                    "Account", "finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
            transactionService.savePrimaryDepositTransaction(primaryTransaction);
        }
        else if(accountType.equalsIgnoreCase("Savings")){
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);
            SavingsTransaction savingsTransaction = new SavingsTransaction(new Date(), "Deposit to Savings Account",
                    "Account", "finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingsDepositTransaction(savingsTransaction);
        }
    }

    public void withdraw(String accountType, double amount, Principal principal){
        User user = userService.findByUsername(principal.getName());

        if(accountType.equalsIgnoreCase("Primary")){
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(new Date(), "Withdrawn from Primary Account",
                    "Account", "finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
            transactionService.savePrimaryWithdrawTransaction(primaryTransaction);
        }
        else if(accountType.equalsIgnoreCase("Savings")){
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);
            SavingsTransaction savingsTransaction = new SavingsTransaction(new Date(), "Withdrawn from Savings Account",
                    "Account", "finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingsWithdrawTransaction(savingsTransaction);
        }
    }
}
