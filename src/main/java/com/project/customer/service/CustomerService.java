package com.project.customer.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import com.project.customer.entity.CustomerEntity;
import com.project.customer.exception.CustomerNotFoundException;
import com.project.customer.repository.CustomerRepository;
import com.project.customer.client.AuthServiceClient;
import com.project.customer.dto.UserDTO;

@Service
public class CustomerService {
	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired //autowire repo
	CustomerRepository customerRepo;
	
	@Autowired
	private AuthServiceClient authServiceClient;
	
	public CustomerEntity addCustomer(CustomerEntity customer) {
		customerRepo.saveAndFlush(customer);
		return customer;
	}
	public List<CustomerEntity> getAllCustomers(){
		return customerRepo.findAll();
	}
	
	public CustomerEntity getACustomer(Long customerId) {
		Optional<CustomerEntity> opt = customerRepo.findById(customerId);

		if(opt.isPresent()) {
		return opt.get();
		}
		else {
			throw new CustomerNotFoundException("Customer with id " + customerId + "  does not exist");
		}
	}
	public String deleteACustomer(Long customerId) {
		CustomerEntity customer=getACustomer(customerId);
	
		customerRepo.deleteById(customerId);
	// if id does not exist throw error
		return "Deleted Successfully";
	}
	public CustomerEntity updateACustomer(CustomerEntity customer) {
		CustomerEntity c = getACustomer(customer.getCustomerId());
		BeanUtils.copyProperties(customer , c);
//		deleteACustomer(customer.getCustomerId());
		return customerRepo.save(c);
		// need to verify
		
	}
	
	public CustomerEntity createCustomerFromUser(Long userId) {
		log.info("Attempting to create customer for userId: {}", userId);
		
		try {
			// Check if customer already exists for this userId
			if (customerRepo.findByUserId(userId).isPresent()) {
				log.error("Customer already exists for userId: {}", userId);
				throw new RuntimeException("Customer already exists for this user");
			}

			// Get user data from auth service
			log.info("Fetching user data from auth service");
			UserDTO user = authServiceClient.getUserById(userId);
			log.info("Received user data: {}", user);
			
			// Create new customer entity
			CustomerEntity customer = new CustomerEntity();
			customer.setUserId(userId);
			customer.setFirstName(user.getFirstName());
			customer.setLastName(user.getLastName());
			customer.setEmail(user.getEmail());
			customer.setPhone(user.getMobileNumber());
			customer.setGender(user.getGender());
			
			log.info("Saving new customer: {}", customer);
			return customerRepo.save(customer);
			
		} catch (Exception e) {
			log.error("Error creating customer: ", e);
			throw new RuntimeException("Failed to create customer: " + e.getMessage());
		}
	}

}
