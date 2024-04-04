package org.demoProject.repository;

import java.util.List;
import java.util.Optional;

import org.demoProject.model.Customer;
import org.demoProject.model.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

//	public Customer addCustomer(Customer customer);
//
//	public Customer updateCustomer(Customer customer);
//
//	public Customer deleteCustomer(Integer customerId);
//
//	public Customer findById(Integer customerId);
//
//	public List<Customer> findAll();

//	public Customer validateCustomer(String username, String password);

	public Customer findByuserName(String userName);
	
	public Optional<Customer> findByUserNameAndPassword(String userName,String password);
	
}
