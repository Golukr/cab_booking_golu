package org.demoProject.service;

import java.util.List;

import org.demoProject.model.Address;
import org.demoProject.repository.AddressRepository;
import org.demoProject.repository.SqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqlServiceImpl{
	
	
	@Autowired
	SqlRepository sqlRepository;
	
	@Autowired
	AddressRepository addressRepository;

	public void updateAddress() {
		List<Address> address = sqlRepository.findAll();
		addressRepository.saveAll(address);
		System.out.println("Address Table updated");
	}
//
//	@Override
//	public void updateAdmin() {
//		System.out.println("Admin table updated");
//		
//	}
//
//	@Override
//	public List<Address> getAddressTable() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
