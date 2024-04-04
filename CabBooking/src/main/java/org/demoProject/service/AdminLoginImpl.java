package org.demoProject.service;

import java.util.Optional;

import org.demoProject.dto.LoginToken;
import org.demoProject.model.Admin;
import org.demoProject.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginImpl {
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin checkLogin(LoginToken loginToken) {
		Optional<Admin> userOpt = adminRepository.findByUserNameAndPassword(loginToken.getUserName(), loginToken.getPassword());
		if(userOpt.isPresent())
			return userOpt.get();
		else
			return null;
	}
	
	public Admin getUserByUserId(Integer userid) {
		Optional<Admin> userOpt = adminRepository.findById(userid);
		if(userOpt.isPresent())
			return userOpt.get();
		else
			return null;
	}
}
