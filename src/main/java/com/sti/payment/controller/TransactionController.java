package com.sti.payment.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
import com.sti.payment.dao.TransactionDao;
import com.sti.payment.dao.model.Account;
import com.sti.payment.dao.model.Transaction;
import com.sti.payment.dto.CommonResponse;
import com.sti.payment.dto.TransactionDto;
import com.sti.payment.exceptioncustom.CustomExceptionTest;

@RestController
@RequestMapping("/transaction")
@SuppressWarnings("rawtypes")
public class TransactionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private TransactionDao transactionDao;
	
	@GetMapping(value="{id}")
	public CommonResponse getById(@PathVariable ("id") String id) throws CustomExceptionTest{
		LOGGER.info("id : {} ", id);
		try{
			Transaction transaction = transactionDao.getById(Integer.parseInt(id));
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, 
					TransactionDto.class));
	} catch (CustomExceptionTest e) {
		LOGGER.error(e.getMessage());
		return new CommonResponse("01", e.getMessage());
	} catch (NumberFormatException e){
		LOGGER.error(e.getMessage());
		return new CommonResponse("06", "parameter harus angka");
	}catch(Exception e) {
		LOGGER.error(e.getMessage());
		return new CommonResponse("06", e.getMessage());	
		}
	}
	
	@PostMapping(value="")
	public CommonResponse save(@RequestBody TransactionDto transactionDto) throws CustomExceptionTest{
		try {
			Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
			transaction.setId(0);
			transaction = transactionDao.save(transaction);
			
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, 
					TransactionDto.class));
		} catch (CustomExceptionTest e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@DeleteMapping(value="{id}")
	public CommonResponse delete(@PathVariable ("id") Integer id ) throws CustomExceptionTest{
		try {
			Transaction checkTransaction = transactionDao.getById(id);
			if(checkTransaction == null) {
				return new CommonResponse("06", "Data tidak ditemukan");
			}
			transactionDao.delete(checkTransaction);
			return new CommonResponse();
		} catch (CustomExceptionTest e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@PutMapping(value="")
	public CommonResponse update(@RequestBody TransactionDto transaction) {
		try {
			Transaction checkTransaction = transactionDao.getById(transaction.getId());
			if(checkTransaction == null) {
				return new CommonResponse("14", "data tidak ditemukan");
			}
			if(transaction.getType() != null) {
				checkTransaction.setType(transaction.getType());
			}
			if(transaction.getAmount() !=null) {
				checkTransaction.setAmount(transaction.getAmount());
			}
			if(transaction.getAmountSign() != null) {
				checkTransaction.setAmountSign(transaction.getAmountSign());
			}	
			if(transaction.getAccount() != null) {
				checkTransaction.setAccount(transaction.getAccount());
			}
			checkTransaction = transactionDao.save(checkTransaction);
			return new CommonResponse<TransactionDto>(modelMapper.map(checkTransaction, 
					TransactionDto.class));
		} catch (CustomExceptionTest e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@GetMapping("/list")
	public CommonResponse getListTrx(@RequestParam(name="account", defaultValue="") String transaction) throws CustomExceptionTest{
		try {
			List<Transaction> transactions;
			if(!StringUtils.isEmpty(transaction)) {
				Account checkAccount = accountDao.getById(Integer.parseInt(transaction));
				if (checkAccount == null) {
					throw new CustomExceptionTest("Account tidak ditemukan !");
				}
				transactions = transactionDao.getListByAccount(checkAccount);
			} else {
				LOGGER.info(" transaction get list, params : {}", transaction);
				transactions = transactionDao.getListTrx();
			}
			return new CommonResponse<List<TransactionDto>>(
					transactions.stream().map(trx -> modelMapper.map(trx, TransactionDto.class)).collect(Collectors.toList()));
		} catch (CustomExceptionTest e) {
			throw e;
		} catch (NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
}
