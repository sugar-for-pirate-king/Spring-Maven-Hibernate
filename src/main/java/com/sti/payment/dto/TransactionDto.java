package com.sti.payment.dto;

import com.sti.payment.dao.model.Account;

public class TransactionDto {
	private Integer id;
	private String type;
	private Integer amount;
	private String amountSign;
	private Account account;
	
	public TransactionDto( ) {}
	public TransactionDto(String type, Integer amount, String amountSign, Account account) {
		super();
		this.type = type;
		this.amount = amount;
		this.amountSign = amountSign;
		this.account = account;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getAmountSign() {
		return amountSign;
	}
	public void setAmountSign(String amountSign) {
		this.amountSign = amountSign;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}
