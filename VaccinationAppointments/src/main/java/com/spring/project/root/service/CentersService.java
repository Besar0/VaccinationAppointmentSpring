package com.spring.project.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacces.Centers;
import com.spring.project.root.repository.CentersRepository;

@Service
public class CentersService {
	
	@Autowired
	CentersRepository centersRepository;
	
	public List<Centers> getList(){
		return this.centersRepository.getAll();
	}
	
}
