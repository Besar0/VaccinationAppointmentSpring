package com.spring.project.root.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.spring.project.root.dataacces.Appointments;
import com.spring.project.root.dataacces.Users;

@Repository
public class AppointmentsRepository extends BasicRepository<Appointments>{

	protected AppointmentsRepository() {
		super(Appointments.class);
	}
	
	@Override
	public List<Appointments> getAll(){
		TypedQuery<Appointments> query = entityManager.createNamedQuery("appointments.findAll", Appointments.class);
		List<Appointments> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public Appointments  getById(int id) {
		TypedQuery<Appointments> query = entityManager.createNamedQuery("appointments.findById", Appointments.class);
		query.setParameter("idappointment", id);
		Appointments element = query.getSingleResult();
		return ObjectUtils.isEmpty(element) ? null : element;
	}

	
	public List<Appointments> getAllByUserId(Users iduser){
		TypedQuery<Appointments> query = entityManager.createNamedQuery("appointments.findByIdUser", Appointments.class);
		query.setParameter("iduser", iduser);
		List<Appointments> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public List<Appointments> getAllByUserIdCanceled(Users iduser){
		TypedQuery<Appointments> query = entityManager.createNamedQuery("appointments.findByIdUserCanceled", Appointments.class);
		query.setParameter("iduser", iduser);
		List<Appointments> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public List<Appointments> getAllByUserIdMissed(Users iduser){
		TypedQuery<Appointments> query = entityManager.createNamedQuery("appointments.findByIdUserMissed", Appointments.class);
		query.setParameter("iduser", iduser);
		List<Appointments> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	public List<Appointments> getAllByUserIdDone(Users iduser){
		TypedQuery<Appointments> query = entityManager.createNamedQuery("appointments.findByIdUserDone", Appointments.class);
		query.setParameter("iduser", iduser);
		List<Appointments> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	
	@Transactional
	@Override
	public Appointments save(Appointments appointment) {
		entityManager.persist(appointment);
		return appointment;
	}
	
	@Transactional
	@Override
	public void delete(Appointments appointment) {
		if (entityManager.contains(appointment)){
	        entityManager.remove(appointment);
	    }
	    else{
	        entityManager.remove(entityManager.merge(appointment));
	    }
	}

}
