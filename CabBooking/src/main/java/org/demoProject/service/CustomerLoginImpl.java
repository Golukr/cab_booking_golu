package org.demoProject.service;

import java.util.Optional;

import org.demoProject.dto.LoginToken;
import org.demoProject.model.Admin;
import org.demoProject.model.Customer;
import org.demoProject.repository.AdminRepository;
import org.demoProject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerLoginImpl {
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer checkLogin(LoginToken loginToken) {
		Optional<Customer> userOpt = customerRepository.findByUserNameAndPassword(loginToken.getUserName(), loginToken.getPassword());
		if(userOpt.isPresent())
			return userOpt.get();
		else
			return null;
	}
	
	public Customer getUserByUserId(Integer userid) {
		Optional<Customer> userOpt = customerRepository.findById(userid);
		if(userOpt.isPresent())
			return userOpt.get();
		else
			return null;
	}
}
