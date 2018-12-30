package com.sti.payment.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sti.payment.dao.AccountDao;
import com.sti.payment.dao.CustomerDao;
import com.sti.payment.dao.TransactionDao;
import com.sti.payment.dao.impl.AccountDaoImpl;
import com.sti.payment.dao.impl.CustomerDaoImpl;
import com.sti.payment.dao.impl.TransactionDaoImpl;

@Configuration
public class DaoSpringConfig {

	@Bean
	public CustomerDao customerDao() {
		return new CustomerDaoImpl();
	}
	
	@Bean
	public AccountDao accountDao() {
		return new AccountDaoImpl();
	}
	
	@Bean
	public TransactionDao transactionDao() {
		return new TransactionDaoImpl();
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
                .allowedHeaders("*");
            }
        };
    }
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
