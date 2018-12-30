package com.sti.payment.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sti.payment.dao.model.Account;
import com.sti.payment.dao.model.Customer;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	Account findByaccountNumber(int accountNumber);
	List<Account> findByCustomer(Customer customer);
}
