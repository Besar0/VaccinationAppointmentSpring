package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.spring.project.root.dataacces.Vaccines;

@Repository
public class VaccineRepository extends BasicRepository<Vaccines>{

	protected VaccineRepository() {
		super(Vaccines.class);
	}
	
	@Override
	public List<Vaccines> getAll(){
		TypedQuery<Vaccines> query = entityManager.createNamedQuery("vaccines.findAll", Vaccines.class);
		List<Vaccines> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public List<Vaccines> getAllAvailable(){
		TypedQuery<Vaccines> query = entityManager.createNamedQuery("vaccines.findAllAvailable", Vaccines.class);
		List<Vaccines> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}

}
