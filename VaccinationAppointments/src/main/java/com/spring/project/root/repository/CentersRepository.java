package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.spring.project.root.dataacces.Centers;

@Repository
public class CentersRepository extends BasicRepository<Centers>{

	protected CentersRepository() {
		super(Centers.class);
	}
	
	@Override
	public List<Centers> getAll(){
		TypedQuery<Centers> query = entityManager.createNamedQuery("centers.findAll", Centers.class);
		List<Centers> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}

}
