package org.demoProject.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.demoProject.model.TripBooking;
import org.demoProject.repository.TripBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripBookingServiceImpl implements TripBookingService {

	@Autowired
	private TripBookingRepository repository;

	@Override
	public TripBooking addTripBooking(TripBooking tripBooking) {
//		Optional<TripBooking> opt = repository.findById(tripBooking.getTripBookingId());
//		if (opt.isPresent()) {
//
//			return null;
//		}
//		repository.save(tripBooking);
		return repository.save(tripBooking);
	}

	@Override
	public TripBooking updateTripBooking(TripBooking tripBooking) {
		Optional<TripBooking> opt = repository.findById(tripBooking.getTripBookingId());
		if (opt.isPresent()){
			repository.save(tripBooking);
			return tripBooking;

		}
		return null;
	}

	@Override
	public TripBooking deleteTripBooking(Integer tripBookingId) {
		Optional<TripBooking> opt = repository.findById(tripBookingId);
		if (opt.isPresent()) {
			TripBooking tripBooking = opt.get();
			repository.delete(tripBooking);
			return tripBooking;

		}
		return null;
	}

	@Override
	public List<TripBooking> findAllTrips(Integer customerId) {
		return repository.findByCustomerId(customerId);
	}

	@Override
	public TripBooking findById(Integer tripBookingId) {
		Optional<TripBooking> opt = repository.findById(tripBookingId);
		if (opt.isPresent()) {
			TripBooking trip = opt.get();
			return trip;
		}

		return null;
	}

	@Override
	public List<TripBooking> findByStatusDate(Boolean status, LocalDateTime date) {
		// TODO Auto-generated method stub
		return repository.findByStatusAndDate(status,date);
	}

	@Override
	public List<String> getAllLoc1() {
		
		return repository.getAllLocationFrom();
	}
	
	@Override
	public List<String> getAllLoc2() {
		
		return repository.getAllLocationTo();
	}

	@Override
	public List<String> getAllLoc2AgainstLoc1(String loc1) {
		// TODO Auto-generated method stub
		return repository.getLocation2AgainstLocation1(loc1);
	}

//	@Override
//	public TripBooking calculateBill(Integer customerId) {
//		return repository;
//	}

}
