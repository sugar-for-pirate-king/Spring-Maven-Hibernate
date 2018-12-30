package com.sti.payment.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.payment.dao.CustomerDao;
import com.sti.payment.dao.model.Customer;
import com.sti.payment.dao.repository.CustomerRepository;
import com.sti.payment.exceptioncustom.CustomExceptionTest;

public class CustomerDaoImpl extends BaseImpl implements CustomerDao {
	
	@Autowired
	private CustomerRepository repository;
	
	@Override
	public Customer getById(int id) throws CustomExceptionTest {
		return repository.findOne(id);
	}

	@Override
	public Customer save(Customer cust) throws CustomExceptionTest {
		return repository.save(cust);
	}

	@Override
	public void delete(Customer cust) throws CustomExceptionTest {		
		repository.delete(cust);
	}

	@Override
	public List<Customer> getList() throws CustomExceptionTest {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Customer> query = critB.createQuery(Customer.class);
		Root<Customer> root = query.from(Customer.class);
		query.select(root);
		
		TypedQuery<Customer> q = em.createQuery(query);
		
		return q.getResultList();
	}
	
}
