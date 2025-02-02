package com.project.customer.controller;

import com.project.customer.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.customer.entity.CustomerEntity;
import com.project.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")

public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
//	@PostMapping("/add")
//
//	public ResponseEntity<CustomerEntity> addACustomer(@RequestBody CustomerEntity customer){
//		return new ResponseEntity<CustomerEntity>(customerService.addCustomer(customer) , HttpStatus.OK);
//	}

	@GetMapping("/me")
	public ResponseEntity<CustomerEntity> getCustomerByUsername(@RequestHeader("X-Username") String username) {
		CustomerEntity customer = customerService.getCustomerByUsername(username);

		if (customer != null) {
			return ResponseEntity.ok(customer);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}


	@PostMapping
	public ResponseEntity<CustomerEntity> createCustomer(@RequestBody User customerDetailsDto) {
		return new ResponseEntity<>(customerService.createCustomer(customerDetailsDto) , HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerEntity> getACusotmer(@PathVariable Long id){
		return new ResponseEntity<CustomerEntity>(customerService.getACustomer(id) , HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<CustomerEntity> updateACustomer(@RequestBody CustomerEntity customer){
		return new ResponseEntity<CustomerEntity>(customerService.updateACustomer(customer) , HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteACustomer(@PathVariable Long id){
		
		customerService.deleteACustomer(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
