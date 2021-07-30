package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.spring.project.root.dataacces.Users;

@Repository
public class UsersRepository extends BasicRepository<Users>{

	protected UsersRepository() {
		super(Users.class);
	}
	
	@Override
	public List<Users> getAll(){
		TypedQuery<Users> query = entityManager.createNamedQuery("users.findAll", Users.class);
		List<Users> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public Users getUserByUsername(final String username) {
		TypedQuery<Users> query = entityManager.createNamedQuery("users.findByUsername", Users.class);
		query.setParameter("username", username);
		Users element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}
	
	public Users  getById(int iduser) {
		TypedQuery<Users> query = entityManager.createNamedQuery("users.findByIduser", Users.class);
		query.setParameter("iduser", iduser);
		Users element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}

	
	@Transactional
	@Override
	public Users save(Users user) {
		entityManager.persist(user);
		return user;
	}
	
	@Transactional
	@Override
	public Users update(Users user) {
		entityManager.merge(user);
		return user;
	}


}
