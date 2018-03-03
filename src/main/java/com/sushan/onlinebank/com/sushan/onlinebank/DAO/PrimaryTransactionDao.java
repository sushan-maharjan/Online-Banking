package com.sushan.onlinebank.com.sushan.onlinebank.DAO;

import java.util.List;

import com.sushan.onlinebank.com.sushan.onlinebank.domain.PrimaryTransaction;
import org.springframework.data.repository.CrudRepository;


public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {

    List<PrimaryTransaction> findAll();
}
