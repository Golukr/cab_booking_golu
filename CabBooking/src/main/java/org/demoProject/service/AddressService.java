package org.demoProject.service;

import java.util.List;

import org.demoProject.model.Address;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
	
	public Address findById(Integer addressId);
	public List<Address> findAll();
	public Address findByLocation1AndLocation2(String string,String string2);

}
