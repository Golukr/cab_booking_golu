package org.demoProject.service;

import java.util.List;
import java.util.Optional;

import org.demoProject.model.Address;
import org.demoProject.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository repository;

	@Override
	public Address findById(Integer addressId) {
		
		Optional<Address> opt = repository.findById(addressId);
		if(opt.isPresent())
			return opt.get();
		return  null;
	}

	@Override
	public List<Address> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Address findByLocation1AndLocation2(String location1, String location2) {
		
		return repository.findByLocation1AndLocation2(location1, location2);
	}
	
	

}
