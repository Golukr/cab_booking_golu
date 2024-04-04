package org.demoProject.service;

import java.util.List;
import java.util.Optional;

import org.demoProject.model.Cab;
import org.demoProject.model.TripBooking;
import org.springframework.stereotype.Service;

@Service
public interface CabService {

	public Cab addCab(Cab cab);

	public Cab updateCab(Cab cab);

	public Cab deleteCab(Integer cabId);

	public Cab findById(Integer cabId);

	public List<Cab> findByCarType(String carType);

	public Integer countByCarType(String carType);
	
	public List<Cab> findAll();
	
	public List<String> listAllCarType();
	
	
}
