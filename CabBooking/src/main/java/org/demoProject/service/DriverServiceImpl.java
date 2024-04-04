package org.demoProject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.demoProject.model.Driver;
import org.demoProject.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository repository;

	@Override
	public Driver addDriver(Driver driver) {

		return repository.save(driver);
	}

	@Override
	public Driver updateDriver(Driver driver) {
		Optional<Driver> opt = repository.findById(driver.getDriverId());
		if (opt.isPresent()) {
			repository.save(driver);
			return driver;
		}

		return null;
	}

	@Override
	public Driver deleteDriver(Integer driverId) {
		Optional<Driver> opt = repository.findById(driverId);
		if (opt.isPresent()) {
			Driver driver = opt.get();
			repository.delete(driver);
			return driver;
		}

		return null;
	}

	@Override
	public Driver findById(Integer driverId) {
		return repository.findById(driverId).get();
	}

	@Override
	public List<Driver> getBestDrivers() {
		List<Driver> drivers= repository.findBestDriver();
		if(drivers.size()>0)
			return drivers;
		return null;
	}

	@Override
	public List<Driver> findAll() {
		return repository.findAll();
	}

	@Override
	public Driver findByUserName(String userName) {
		return repository.findByUserName(userName);
	}

	@Override
	public Driver validateDriver(String username, String password) {
		List<Driver> c1  =repository.findAll();
		for(int i= 0; i < c1.size(); i++) {
			if(c1.get(i).getUserName().equals(username) && c1.get(i).getPassword().equals(password))
			     return c1.get(i);
			}
		return null;
	}

	@Override
	public List<Driver> findByCab(Integer cab) {
		return repository.findByCab(cab);
	}

	@Override
	public List<Driver> findAllByOrderByRatingDesc() {
		return repository.findAllByOrderByRatingDesc();
	}

	@Override
	public List<Driver> findByBikeOrderByRating() {
		// TODO Auto-generated method stub
		return repository.findByBikeOrderByRating();
	}

	@Override
	public List<Driver> findBy2SeaterOrderByRating() {
		// TODO Auto-generated method stub
		return repository.findBy2SeaterOrderByRating();
	}

	@Override
	public List<Driver> findBy4SeaterOrderByRating() {
		// TODO Auto-generated method stub
		return repository.findBy4SeaterOrderByRating();
	}

	@Override
	public List<Driver> findBy6SeaterOrderByRating() {
		// TODO Auto-generated method stub
		return repository.findBy6SeaterOrderByRating();
	}

}
