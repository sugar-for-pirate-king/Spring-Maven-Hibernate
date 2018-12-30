package com.sti.payment.dao;

import java.util.List;

import com.sti.payment.dao.model.Account;
import com.sti.payment.dao.model.Transaction;
import com.sti.payment.exceptioncustom.CustomExceptionTest;

public interface TransactionDao {
	Transaction getById(int id) throws CustomExceptionTest;
	Transaction save(Transaction transaction) throws CustomExceptionTest;
	void delete(Transaction transaction) throws CustomExceptionTest;
	
	List<Transaction> getListTrx() throws CustomExceptionTest;
	List<Transaction> getListByAccount(Account account) throws CustomExceptionTest;
}
