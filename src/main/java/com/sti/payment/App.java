package com.sti.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sti.payment.config.DaoSpringConfig;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableJpaRepositories({"com.sti.payment.dao.repository"})
@EntityScan({"com.sti.payment.dao.model"})
@Import(DaoSpringConfig.class)
public class App 
{
	
    public static void main( String[] args ){
        SpringApplication.run(App.class, args);
    }
}
