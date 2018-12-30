package com.sti.payment.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sti.payment.dao.model.Customer;

public class AccountDto {
	private int accountNumber;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date openDate;
	private Double balance;
	private Customer customer;
	
	public AccountDto() {}
	public AccountDto(Date openDate, Double balance, Customer customer) {
		this.openDate = openDate;
		this.balance = balance;
		this.customer = customer;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
