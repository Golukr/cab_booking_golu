package org.demoProject.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.demoProject.model.Admin;
import org.demoProject.model.TripBooking;
import org.demoProject.repository.AdminRepository;
import org.demoProject.repository.CustomerRepository;
import org.demoProject.repository.TripBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repository;
	@Autowired
	private CustomerRepository customerrepo;

	@Autowired
	private TripBookingRepository triprepo;

	@Override
	public Admin addAdmin(Admin admin) {

//		Optional<Admin> opt = repository.findById(admin.getAdminId());
//		if(opt.isPresent()) {
//			return null;
//		}
		return repository.save(admin);

	}

	@Override
	public Admin updateAdmin(Admin admin) {
		return repository.save(admin);
//		Optional<Admin> opt = repository.findById(admin.getAdminId());
//		if(opt.isPresent()) {
//			repository.save(opt.get());
//			return admin;
//		}
//		return null;
	}

	@Override
	public Admin deleteAdmin(Integer adminId) {
		Optional<Admin> opt = repository.findById(adminId);
		if (opt.isPresent()) {
			Admin admin = opt.get();
			repository.delete(admin);
			return admin;
		}
		return null;
	}

	@Override
	public List<TripBooking> findAllTripsDriverwise() {
		// TODO Auto-generated method stub
		List<TripBooking> list = triprepo.findByDriverAsc();
		return list;
	}

	@Override
	public List<TripBooking> findAllTripsDatewise() {

		List<TripBooking> list = triprepo.findByFromdate_timeAsce();
		return list;
	}

//	@Override
//	public List<TripBooking> findAllTripsForDayes(Integer customerId, LocalDate fromDate, LocalDate toDate) {
//		
//		List<TripBooking> list =triprepo.findByCustomerIdAndFromdate_time(customerId,fromDate, toDate);
//		if(list.size() > 0)
//			return list;
//		return null;
//	}

//	@Override
//	public List<Admin> findAll() {
//		return repository.findAll();
//	}
//
	@Override
	public Admin findById(Integer adminId) {
		Optional<Admin> opt = repository.findById(adminId);
		if (opt.isPresent()) {
			Admin admin = opt.get();
			return admin;
		}
		return null;
	}

	@Override
	public Admin findAdminbyusername(String username) {
		return repository.findByuserName(username);
	}

	@Override
	public Admin validateAdmin(String userName, String password) {
//		List<Admin> c1 = repository.findAll();
//		for (int i = 0; i < c1.size(); i++) {
//			if (c1.get(i).getUserName().equals(userName) && c1.get(i).getPassword().equals(password))
//				return c1.get(i);
//		}
//		return null;
		return repository.findByUserNameAndPassword(userName, password).get();
	}

	@Override
	public Admin findByuserName(String username) {
		return repository.findByuserName(username);
	}

	@Override
	public List<TripBooking> findAllTripsCustomerwise() {
		return repository.findAllTripsCustomerwise();
	}

	@Override
	public List<TripBooking> findAllTripsofcustomer(Integer customerId) {
		return repository.findByCustomerId(customerId);
	}

	@Override
	public Optional<Admin> findByUserNameAndPassword(String userName, String password) {
		return repository.findByUserNameAndPassword(userName, password);
	}

}
