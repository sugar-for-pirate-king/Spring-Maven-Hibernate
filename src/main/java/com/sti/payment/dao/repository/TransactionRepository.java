package com.sti.payment.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sti.payment.dao.model.Account;
import com.sti.payment.dao.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	Transaction findByid(int id);
	List<Transaction> findByAccount(Account account);
}
