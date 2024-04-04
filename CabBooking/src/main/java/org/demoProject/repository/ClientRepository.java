package org.demoProject.repository;

import org.demoProject.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
	
	Client findByName(String name);

}
