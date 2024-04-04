package org.demoProject.repository;

import java.util.List;
import java.util.Optional;

import org.demoProject.model.Cab;
import org.demoProject.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

//	public Driver addDriver(Driver driver);
//
//	public Driver updateDriver(Driver driver);
//
//	public Driver deleteDriver(Integer driverId);
//
//	public Driver findById(Integer driverId);

//	public Driver validateDriver(Driver driver);
	
	public Driver findByUserName(String userName);
	
	public List<Driver> findByCab(Integer cab);

	@Query("from Driver d where d.rating>=4.5")
	public List<Driver> findBestDriver();
	
	public List<Driver> findAllByOrderByRatingDesc();
	
	@Query("SELECT distinct d FROM Driver d, Cab c where c.carType = 'bike' and c.cabId = d.cab.cabId order by d.rating desc")
	public List<Driver> findByBikeOrderByRating();
	
	@Query("SELECT distinct d FROM Driver d, Cab c where c.carType = '2-seater' and c.cabId = d.cab.cabId order by d.rating desc")
	public List<Driver> findBy2SeaterOrderByRating();
	
	@Query("SELECT distinct d FROM Driver d, Cab c where c.carType = '4-seater' and c.cabId = d.cab.cabId order by d.rating desc")
	public List<Driver> findBy4SeaterOrderByRating();
	
	@Query("SELECT distinct d FROM Driver d, Cab c where c.carType = '6-seater' and c.cabId = d.cab.cabId order by d.rating desc")
	public List<Driver> findBy6SeaterOrderByRating();
	
	public Optional<Driver> findByUserNameAndPassword(String userName,String password);
}
