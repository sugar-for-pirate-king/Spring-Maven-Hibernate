package com.sti.payment.dao;

import java.util.List;

import com.sti.payment.dao.model.Customer;
import com.sti.payment.exceptioncustom.CustomExceptionTest;

public interface CustomerDao {
	public Customer getById(int id) throws CustomExceptionTest;
	public Customer save(Customer cust) throws CustomExceptionTest;
	void delete(Customer cust) throws CustomExceptionTest;
	List<Customer> getList() throws CustomExceptionTest;
}
