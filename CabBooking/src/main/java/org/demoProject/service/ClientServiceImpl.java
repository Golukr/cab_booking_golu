package org.demoProject.service;

import java.util.List;

import org.demoProject.model.Client;
import org.demoProject.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	ClientRepository clientRepository;

	@Override
	public Client findById(Integer id) {
		return clientRepository.findById(id).get();
	}

	@Override
	public Client findByName(String name) {
		return clientRepository.findByName(name);
	}

	@Override
	public List<Client> findAll() {
		return clientRepository.findAll();
	}
}
