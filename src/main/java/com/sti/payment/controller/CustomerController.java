package com.sti.payment.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.payment.dao.CustomerDao;
import com.sti.payment.dao.model.Customer;
import com.sti.payment.dto.CommonResponse;
import com.sti.payment.dto.CustomerDto;
import com.sti.payment.exceptioncustom.CustomExceptionTest;

@RestController
@RequestMapping("/customer")
@SuppressWarnings("rawtypes")
public class CustomerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping(value="/{customerNumber}")
	public CommonResponse getById(@PathVariable("customerNumber") String customerNumber) throws CustomExceptionTest {
		LOGGER.info("customerNumber : {}", customerNumber);
		try {
			Customer customer = customerDao.getById(Integer.parseInt(customerNumber));
			
			return new CommonResponse<CustomerDto>(modelMapper.map(customer, 
					CustomerDto.class));
		} catch (CustomExceptionTest e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", "parameter harus angka");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	@PostMapping(value="")
	public CommonResponse save(@RequestBody CustomerDto customerDto) throws CustomExceptionTest{
		
		try {
			Customer customer = modelMapper.map(customerDto, Customer.class);
			customer.setCustomerNumber(0);
			customer =  customerDao.save(customer);
			
			return new CommonResponse<CustomerDto>(modelMapper.map(customer,
					CustomerDto.class));
		} catch (CustomExceptionTest e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}
	
	@GetMapping("/list")
	public CommonResponse getList(@RequestParam(name="customer", defaultValue="") String customer) throws CustomExceptionTest{
		try {
			LOGGER.info("customer get list, params : {}", customer);
			List<Customer> customers = customerDao.getList();
		
			return new CommonResponse<List<CustomerDto>>(customers.stream().map(cust -> 
			modelMapper.map(cust, CustomerDto.class)).collect(Collectors.toList()));
		} catch (CustomExceptionTest e) {
			throw e;
		} catch(NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	
	@DeleteMapping(value="/{id}")
	public CommonResponse delete(@PathVariable ("id") Integer customerNumber) throws CustomExceptionTest {
		try {
			Customer checkCustomer = customerDao.getById(customerNumber);
			if(checkCustomer == null) {
				return new CommonResponse("06", "customer tidak ditemukan");
			}
			customerDao.delete(checkCustomer);
			return new CommonResponse();
		} catch (CustomExceptionTest e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}
	
	@PutMapping(value="")
	public CommonResponse update(@RequestBody CustomerDto customer) throws CustomExceptionTest {
		try {
			Customer checkCustomer = customerDao.getById(customer.getCustomerNumber());
			if(checkCustomer == null) {
				return new CommonResponse("14", "customer tidak ditemukan");
			}
			
			if(customer.getBirthDate()!=null) {
				checkCustomer.setBirthDate(customer.getBirthDate());
			}
			if(customer.getFirstName()!=null) {
				checkCustomer.setFirstName(customer.getFirstName());
			}
			if(customer.getLastName()!=null) {
				checkCustomer.setLastName(customer.getLastName());
			}
			if(customer.getPhoneNumber()!=null) {
				checkCustomer.setPhoneNumber(customer.getPhoneNumber());
			}
			if(customer.getPhoneType()!=null) {
				checkCustomer.setPhoneType(customer.getPhoneType());
			}
			
			checkCustomer =  customerDao.save(checkCustomer);
			
			return new CommonResponse<CustomerDto>(modelMapper.map(checkCustomer, 
					CustomerDto.class));
		} catch (CustomExceptionTest e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
}
