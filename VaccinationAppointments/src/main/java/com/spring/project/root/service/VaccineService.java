package com.spring.project.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacces.Vaccines;
import com.spring.project.root.repository.VaccineRepository;

@Service
public class VaccineService {

	@Autowired
	VaccineRepository vaccineRepository;
	
	public List<Vaccines> getListAvailable(){
		return this.vaccineRepository.getAllAvailable();
	}
	
}
