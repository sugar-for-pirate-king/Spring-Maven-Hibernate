package com.sti.payment.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.payment.dao.TransactionDao;
import com.sti.payment.dao.model.Account;
import com.sti.payment.dao.model.Transaction;
import com.sti.payment.dao.repository.TransactionRepository;
import com.sti.payment.exceptioncustom.CustomExceptionTest;

public class TransactionDaoImpl extends BaseImpl implements TransactionDao {
	
	@Autowired
	private TransactionRepository repository;
	
	@Override
	public Transaction getById(int id) throws CustomExceptionTest {
		return repository.findByid(id);
	}

	@Override
	public Transaction save(Transaction transaction) throws CustomExceptionTest {
		return repository.save(transaction);
	}

	@Override
	public void delete(Transaction transaction) throws CustomExceptionTest {
			repository.delete(transaction);
	}

	@Override
	public List<Transaction> getListTrx() throws CustomExceptionTest {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = critB.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root);
		
		TypedQuery<Transaction> q = em.createQuery(query);
		
		return q.getResultList();
	}

	@Override
	public List<Transaction> getListByAccount(Account account) throws CustomExceptionTest {
		return repository.findByAccount(account);
	}	
	
}
