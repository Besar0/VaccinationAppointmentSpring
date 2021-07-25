package com.spring.project.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacces.Citizens;
import com.spring.project.root.repository.CitizenRepository;

@Service
public class CitizenService {
	
	@Autowired
	CitizenRepository citizenRepository;
	
	public List<Citizens> getList(){
		return this.citizenRepository.getAll();
	}
	
}
