package com.sushan.onlinebank.com.sushan.onlinebank.service.UserServiceImpl;

import com.sushan.onlinebank.com.sushan.onlinebank.DAO.PrimaryTransactionDao;
import com.sushan.onlinebank.com.sushan.onlinebank.DAO.SavingsTransactionDao;
import com.sushan.onlinebank.com.sushan.onlinebank.domain.PrimaryTransaction;
import com.sushan.onlinebank.com.sushan.onlinebank.domain.SavingsTransaction;
import com.sushan.onlinebank.com.sushan.onlinebank.domain.User;
import com.sushan.onlinebank.com.sushan.onlinebank.service.TransactionService;
import com.sushan.onlinebank.com.sushan.onlinebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private UserService userService;

	@Autowired
	private PrimaryTransactionDao primaryTransactionDao;

	@Autowired
	private SavingsTransactionDao savingsTransactionDao;

	public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
		User user = userService.findByUsername(username);
		List<PrimaryTransaction> primaryTransactionList = user.getPrimaryAccount().getPrimaryTransactionList();

		return primaryTransactionList;
	}

	public List<SavingsTransaction> findSavingsTransactionList(String username) {
		User user = userService.findByUsername(username);
		List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionList();

		return savingsTransactionList;
	}

	public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
	}

	public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);
	}

	public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
	}

	public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);
	}
}