package org.demoProject.service;

import java.util.Optional;

import org.demoProject.dto.LoginToken;
import org.demoProject.model.Driver;
import org.demoProject.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverLoginImpl {

	@Autowired
	private DriverRepository driverRepository;
	
	public Driver checkLogin(LoginToken loginToken) {
		Optional<Driver> userOpt = driverRepository.findByUserNameAndPassword(loginToken.getUserName(), loginToken.getPassword());
		if(userOpt.isPresent())
			return userOpt.get();
		else
			return null;
	}
	
	public Driver getUserByUserId(Integer userid) {
		Optional<Driver> userOpt = driverRepository.findById(userid);
		if(userOpt.isPresent())
			return userOpt.get();
		else
			return null;
	}
}
