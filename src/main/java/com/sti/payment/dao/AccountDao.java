package com.sti.payment.dao;

import java.util.List;

import com.sti.payment.dao.model.Account;
import com.sti.payment.dao.model.Customer;
import com.sti.payment.exceptioncustom.CustomExceptionTest;

public interface AccountDao {
	Account getById(int id) throws CustomExceptionTest;
	Account save(Account account) throws CustomExceptionTest;
	void delete(Account account) throws CustomExceptionTest;
	
	List<Account> getList() throws CustomExceptionTest;
	List<Account> getListByCustomer(Customer customer) throws CustomExceptionTest;
}
