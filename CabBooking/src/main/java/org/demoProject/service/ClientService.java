package org.demoProject.service;

import java.util.List;

import org.demoProject.model.Client;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
	
	Client findById(Integer id);

	Client findByName(String name);
	
	List<Client> findAll();
}
