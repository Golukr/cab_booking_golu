package org.demoProject.repository;

import java.util.List;

import org.demoProject.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlRepository extends JpaRepository<Address, Integer>{
	
//	@Query("INSERT INTO Address VALUES (1,'01:15:00',100,'delhi','banglore'),(2,'01:15:00',100,'banglore','delhi'),(3,'00:45:00',60,'haldia','kolkata'),(4,'00:45:00',60,'kolkata','haldia'),(7,'13:45:00',100,'delhi','haldia'),(8,'13:45:00',100,'haldia','delhi'),(9,'17:45:00',650,'delhi','kolkata'),(10,'17:45:00',650,'kolkata','delhi'),(11,'19:45:00',740,'banglore','kolkata'),(12,'19:45:00',740,'kolkata','banglore'),(13,'02:15:00',150,'banglore','haldia'),(14,'02:15:00',150,'haldia','banglore')")
//	public void updateAddress();
//	
//	@Query("INSERT INTO Admin VALUES (1,'iamakash2nd@gmail.com','+917004552640','Akash@123','Admin')")
//	public void updateAdmin();
//	
//	@Query("Select * from Address")
//	public List<Address> getAddressTable();

}
