package com.project.customer.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_entity")
public class CustomerEntity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "customer_id")
	    private Long customerId;

		@Column(name = "user_id")
		private long userId;

		@Column(name = "username")
		private String username;

	    @Column(name = "full_name")
	    private String fullName;


	    @Column(name = "email")
	    private String email;

	    @Column(name = "phone")
	    private String phone;

	    @Column(name = "address")
	    private String address;

	    @Lob
	    @Column(name = "picture")
	    private byte[] picture;

	    @Column(name = "age")
	    private String age;

	    @Column(name = "gender")
	    private String gender;

	public CustomerEntity() {
	}

	public CustomerEntity(Long customerId, long userId, String username, String fullName, String phone, String email, String address, byte[] picture, String age, String gender) {
		this.customerId = customerId;
		this.userId = userId;
		this.username = username;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.picture = picture;
		this.age = age;
		this.gender = gender;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// Getters and setters
	    public Long getCustomerId() {
	        return customerId;
	    }

	    public void setCustomerId(Long customerId) {
	        this.customerId = customerId;
	    }

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public byte[] getPicture() {
	        return picture;
	    }

	    public void setPicture(byte[] picture) {
	        this.picture = picture;
	    }




	    public String getGender() {
	        return gender;
	    }

	    public void setGender(String gender) {
	        this.gender = gender;
	    }

	   
	    
	}


