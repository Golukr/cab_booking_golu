package org.demoProject.repository;

import java.util.List;

import org.demoProject.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CabRepository extends JpaRepository<Cab, Integer>{
	
//	public Cab addCab(Cab cab);
//
//	public Cab updateCab(Cab cab);
//
//	public Cab deleteCab(Integer cabId);
//
	public List<Cab> findByCarType(String carType);

	public Integer countByCarType(String carType);

	@Query("SELECT distinct carType FROM Cab")
	public List<String> listAllCarType();


}
