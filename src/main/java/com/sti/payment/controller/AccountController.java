package com.sti.payment.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.payment.dao.AccountDao;
import com.sti.payment.dao.CustomerDao;
import com.sti.payment.dao.model.Account;
import com.sti.payment.dao.model.Customer;
import com.sti.payment.dto.AccountDto;
import com.sti.payment.dto.CommonResponse;
import com.sti.payment.exceptioncustom.CustomExceptionTest;

@RestController
@RequestMapping("/account")
@SuppressWarnings("rawtypes")
public class AccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private CustomerDao customerDao;
	
	
	@GetMapping(value="/{accountNumber}")
	public CommonResponse getById(@PathVariable ("accountNumber") String accountNumber) throws CustomExceptionTest {
		LOGGER.info("accountNumber : {}", accountNumber);
		try{
			Account account = accountDao.getById(Integer.parseInt(accountNumber));
			return new CommonResponse<AccountDto>(modelMapper.map(account, 
					AccountDto.class));
		} catch (CustomExceptionTest e){
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		} catch(NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", "parameter harus angka");	
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
}
		
	@PostMapping(value="")
	public CommonResponse save(@RequestBody AccountDto accountDto) throws CustomExceptionTest{
		try {
			Account account =  modelMapper.map(accountDto, Account.class);
			account.setAccountNumber(0);
			account =  accountDao.save(account);
			
			return new CommonResponse<AccountDto>(modelMapper.map(account,
					AccountDto.class));
		} catch (CustomExceptionTest e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	
	@GetMapping(value="/list")
	public CommonResponse getList(@RequestParam(name="customer", defaultValue="") String account) throws CustomExceptionTest {
		try {
			List<Account> accounts;
			if(!StringUtils.isEmpty(account)) {
				Customer checkCustomer = customerDao.getById(Integer.parseInt(account));
				if(checkCustomer == null) {
					throw new CustomExceptionTest("Customer tidak ditemukan !");
				}
				accounts = accountDao.getListByCustomer(checkCustomer);
			}else {
				LOGGER.info("account get list, params : {}", account);
				accounts = accountDao.getList();
			}		
			return new CommonResponse<List<AccountDto>>(
					accounts.stream().map(acc -> modelMapper.map(acc, AccountDto.class)).collect(Collectors.toList()));
			
		} catch (CustomExceptionTest e) {
			throw e;
		} catch(NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@DeleteMapping(value="/{account}")
	public CommonResponse delete(@PathVariable ("account")Integer accountNumber) throws CustomExceptionTest {
		try {
			Account checkAccount = accountDao.getById(accountNumber);
			if(checkAccount == null) {
				return new CommonResponse("06", "customer tidak ditemukan");
			}
			accountDao.delete(checkAccount);
			return new CommonResponse();
		} catch (CustomExceptionTest e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@PutMapping(value="")
	public CommonResponse update(@RequestBody AccountDto account) throws CustomExceptionTest {
		try {
			Account checkAccount = accountDao.getById(account.getAccountNumber());
			if(checkAccount == null) {
				return new CommonResponse("14", "data tidak ditemukan");
			}
			if(account.getOpenDate() !=null) {
				checkAccount.setOpenDate(account.getOpenDate());
			}
			if(account.getBalance() != null) {
				checkAccount.setBalance(account.getBalance());
			}
			if(account.getCustomer() != null) {
				checkAccount.setCustomer(account.getCustomer());
			}
			checkAccount = accountDao.save(checkAccount);
			
			return new CommonResponse<AccountDto>(modelMapper.map(checkAccount, 
					AccountDto.class));
		}catch(CustomExceptionTest e) {
			return new CommonResponse("01", e.getMessage());
		}catch(Exception e) {
		return new CommonResponse("06", e.getMessage());
		}
		}
}
