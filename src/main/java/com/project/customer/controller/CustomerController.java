package com.project.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.customer.entity.CustomerEntity;
import com.project.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")

public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/add")
	
	public ResponseEntity<CustomerEntity> addACustomer(@RequestBody CustomerEntity customer){
		return new ResponseEntity<CustomerEntity>(customerService.addCustomer(customer) , HttpStatus.OK);
	}

}
