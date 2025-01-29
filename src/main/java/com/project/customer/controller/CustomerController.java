package com.project.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.customer.entity.CustomerEntity;
import com.project.customer.service.CustomerService;
import com.project.customer.client.LoanApplicationClient;
import com.project.customer.dto.LoanApplicationDTO;

import java.util.List;

@RestController
@RequestMapping("/customers")

public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LoanApplicationClient loanApplicationClient;
	
	@PostMapping("/add")
	
	public ResponseEntity<CustomerEntity> addACustomer(@RequestBody CustomerEntity customer){
		return new ResponseEntity<CustomerEntity>(customerService.addCustomer(customer) , HttpStatus.OK);
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

	@GetMapping("/user/{id}/loans")
	public ResponseEntity<List<LoanApplicationDTO>> getCustomerLoans(@PathVariable Long id) {
		// First verify customer exists
		CustomerEntity customer = customerService.getACustomer(id);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		List<LoanApplicationDTO> loans = loanApplicationClient.getLoansByCustomerId(id);
		return ResponseEntity.ok(loans);
	}

	@PostMapping("/link/{userId}")
	public ResponseEntity<CustomerEntity> createCustomerForUser(@PathVariable Long userId, 
															  @RequestBody CustomerEntity customer) {
		customer.setUserId(userId);
		return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.OK);
	}

	@PostMapping("/create-from-user/{userId}")
	public ResponseEntity<CustomerEntity> createCustomerFromUser(@PathVariable Long userId) {
		try {
			CustomerEntity customer = customerService.createCustomerFromUser(userId);
			return new ResponseEntity<>(customer, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
