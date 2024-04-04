package org.demoProject.service;

import java.util.List;
import java.util.Optional;

import org.demoProject.model.Cab;
import org.demoProject.repository.CabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class CabServiceImpl implements CabService{

	@Autowired
	private CabRepository repository;
	
	@Override
	public Cab addCab(Cab cab) {
	
		repository.save(cab);
		return cab;
	}

	@Override
	public Cab updateCab(Cab cab) {
		Optional<Cab> opt = repository.findById(cab.getCabId());
		if(opt.isPresent()) {
			repository.save(cab);
			return cab;
		}
		return null;
	}

	@Override
	public Cab deleteCab(Integer cabId) {
		Optional<Cab> opt = repository.findById(cabId);
		if(opt.isPresent()) {
			Cab cab = opt.get();
			repository.delete(cab);
			return cab;
		}
		return null;
	}

	@Override
	public Cab findById(Integer cabId) {
		Optional<Cab> opt = repository.findById(cabId);
		if(opt.isPresent()) {
			Cab cab = opt.get();
			return cab;
		}
		return null;
	}

	@Override
	public List<Cab> findByCarType(String carType) {
		
		return repository.findByCarType(carType);
	}

	@Override
	public Integer countByCarType(String carType) {
		
		return repository.countByCarType(carType);
	}

	@Override
	public List<Cab> findAll() {
		return repository.findAll();
	}

	@Override
	public List<String> listAllCarType() {
		return repository.listAllCarType();
	}

}
