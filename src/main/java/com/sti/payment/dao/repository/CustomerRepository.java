package com.sti.payment.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sti.payment.dao.model.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
	Customer findByCustomerNumber(int customerNumber);
}
