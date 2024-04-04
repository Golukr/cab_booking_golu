package org.demoProject.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.demoProject.model.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TripBookingRepository extends JpaRepository<TripBooking, Integer>{
	
//	public TripBooking addTripBooking(TripBooking tripBooking);
//
//	public TripBooking updateTripBooking(TripBooking tripBooking);
//
//	public TripBooking deleteTripBooking(Integer tripBookingId);

	@Query("from TripBooking where customer_id_customer_id =:customerId order by from_date_time")
	public List<TripBooking> findByCustomerId(@Param("customerId")Integer customerId);
	
	@Query("from TripBooking order by customer_id_customer_id")
	public List<TripBooking> findtripscustomerwise();

	@Query("from TripBooking  order by fromDateTime")
	public List<TripBooking> findByFromdate_timeAsce();
	
	//@Query("from TripBooking where customerId=:customerId AND1 fromDateTime=date AND toDateTime=toDate")
	//public List<TripBooking> findByCustomerIdAndFromdate_time(Integer customerId, LocalDate date,LocalDate toDate);
	
	@Query("from TripBooking  ORDER by driver_id_driver_id")
	public List<TripBooking> findByDriverAsc();
	
	@Query("from TripBooking where status = :status and :fromDate < toDateTime")
	public List<TripBooking> findByStatusAndDate(Boolean status, LocalDateTime fromDate);
	
	@Query("Select distinct location1 from Address order by location1")
	public List<String> getAllLocationFrom();
	
	@Query("Select distinct location2 from Address order by location2")
	public List<String> getAllLocationTo();
	
	@Query("Select location2 from Address where location1 =: fromLocation order by location2")
	public List<String> getLocation2AgainstLocation1(String fromLocation);
}
