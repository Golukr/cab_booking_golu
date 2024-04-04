package org.demoProject.service;

import java.util.List;
import java.util.Optional;

import org.demoProject.model.Customer;
import org.demoProject.model.TripBooking;
import org.demoProject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
//@Component("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Override
	public Customer addCustomer(Customer customer) {
//		Optional<Customer> opt = repository.findById(customer.getCustomerId());
//		if (opt.isPresent()) {
//
//			return null;
//		}
//		repository.save(customer);
//		return customer;
		return repository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Optional<Customer> opt = repository.findById(customer.getCustomerId());
		if (opt.isPresent()) {
			repository.save(customer);
			return customer;
		}

		return null;
	}

	@Override
	public Customer deleteCustomer(Integer customerId) {
		Optional<Customer> opt = repository.findById(customerId);
		if (opt.isPresent()) {
			Customer customer = opt.get();
			repository.delete(customer);
			return customer;
		}

		return null;
	}

	@Override
	public Customer findById(Integer customerId) {
		Optional<Customer> opt = repository.findById(customerId);
		if (opt.isPresent()) {
			Customer customer = opt.get();
			return customer;
		}

		return null;
	}

	@Override
	public List<Customer> findAll() {

		return repository.findAll();
	}

	@Override
	public Customer validateCustomer(String username, String password) {
		List<Customer> c1 = repository.findAll();
		for (int i = 0; i < c1.size(); i++) {
			if (c1.get(i).getUserName().equals(username) && c1.get(i).getPassword().equals(password))
				return c1.get(i);
		}
		return null;
	}

	@Override
	public Customer findcustomerbyusername(String username) {
		return repository.findByuserName(username);
	}



}
