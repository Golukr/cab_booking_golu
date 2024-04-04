package org.demoProject.service;

import java.util.List;
import java.util.Optional;

import org.demoProject.model.Admin;
import org.demoProject.model.TripBooking;
import org.springframework.stereotype.Service;



@Service
public interface AdminService {

	public Admin addAdmin(Admin admin);

	public Admin updateAdmin(Admin admin);

	public Admin deleteAdmin(Integer adminId);
	
	public Admin findAdminbyusername(String username);
	
	
	public List<TripBooking> findAllTripsofcustomer(Integer customerId);

	public List<TripBooking> findAllTripsDriverwise();


	public List<TripBooking> findAllTripsDatewise();
	public Admin findByuserName(String username);

	//public List<TripBooking> findAllTripsForDayes(Integer customerId, LocalDate fromDate, LocalDate toDate);

	public Admin findById(Integer adminId);
//	
//	public List<Admin> findAll();

	public Admin validateAdmin(String userName, String password);

	public List<TripBooking> findAllTripsCustomerwise();

	public Optional<Admin> findByUserNameAndPassword(String userName,String password);
}
