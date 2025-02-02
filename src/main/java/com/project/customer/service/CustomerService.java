package com.project.customer.service;

import java.util.List;
import java.util.Optional;

import com.project.customer.dto.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.customer.entity.CustomerEntity;
import com.project.customer.exception.CustomerNotFoundException;
import com.project.customer.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired //autowire repo
	CustomerRepository customerRepo;
	
//	public CustomerEntity addCustomer(CustomerEntity customer) {
//		customerRepo.saveAndFlush(customer);
//		return customer;
//	}


	public CustomerEntity createCustomer(User user) {
		CustomerEntity customer = new CustomerEntity();
		customer.setUserId(user.getUserId()); // Set userId as foreign key
		customer.setUsername(user.getUsername());
		customer.setFullName(user.getFullName());
		customer.setEmail(user.getEmail());
		customer.setAge(user.getAge());
		customer.setGender(user.getGender());
		customer.setPhone(user.getPhone());
		return customerRepo.save(customer);
	}


	public List<CustomerEntity> getAllCustomers(){
		return customerRepo.findAll();
	}
	
	public CustomerEntity getACustomer(Long customerId) {
		Optional <CustomerEntity>opt = customerRepo.findById(customerId);
		CustomerEntity customer = opt.get();
		if(opt.get()!=null) {
		
		return customer;
		}
		else {
			throw new CustomerNotFoundException("Customer with id " + customer.getCustomerId() + "does not exist");
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


	public CustomerEntity getCustomerByUsername(String username) {
		Optional<CustomerEntity> customer = customerRepo.findByUsername(username);
		assert customer.isPresent();
		return customer.get();
	}
}
