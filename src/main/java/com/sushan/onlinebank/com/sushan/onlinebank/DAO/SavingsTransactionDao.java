package com.sushan.onlinebank.com.sushan.onlinebank.DAO;

import java.util.List;

import com.sushan.onlinebank.com.sushan.onlinebank.domain.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;


public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction, Long> {

    List<SavingsTransaction> findAll();
}

