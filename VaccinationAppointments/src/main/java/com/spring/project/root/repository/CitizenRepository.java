package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.spring.project.root.dataacces.Citizens;

@Repository
public class CitizenRepository extends BasicRepository<Citizens>{
	
	protected CitizenRepository() {
		super(Citizens.class);
	}

	@Override
	public List<Citizens> getAll(){
		TypedQuery<Citizens> query = entityManager.createNamedQuery("citizens.findAll", Citizens.class);
		List<Citizens> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
}
