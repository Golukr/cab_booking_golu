package org.demoProject.repository;

import org.demoProject.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Integer>{
	
	public Address findByLocation1AndLocation2(String location1,String location2);

}
