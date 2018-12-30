package com.sti.payment.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="customer")
public class Customer {
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="customer_number")
		private int customerNumber;
		@Column(name="first_name")
		private String firstName;
		@Column(name="last_name")
		private String lastName;
		@Temporal(TemporalType.DATE)
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd", timezone="EST")
		@Column(name="birth_date")
		private Date birthDate;
		@Column(name="username")
		private String username;
		@Column(name="password")
		private String password;
		@Column(name="phone_number")
		private String phoneNumber; 
		@Column(name="phone_type")
		private String phoneType;
		
		public Customer() {}
		public Customer(String firstName, String lastName, Date birthDate, String username, String password,
				String phoneNumber, String phoneType) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.birthDate = birthDate;
			this.username = username;
			this.password = password;
			this.phoneNumber = phoneNumber;
			this.phoneType = phoneType;
		}
		public int getCustomerNumber() {
			return customerNumber;
		}
		public void setCustomerNumber(int customerNumber) {
			this.customerNumber = customerNumber;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public Date getBirthDate() {
			return birthDate;
		}
		public void setBirthDate(Date birthDate) {
			this.birthDate = birthDate;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getPhoneType() {
			return phoneType;
		}
		public void setPhoneType(String phoneType) {
			this.phoneType = phoneType;
		}
		
		
}
