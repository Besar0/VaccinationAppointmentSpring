package com.spring.project.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacces.Appointments;
import com.spring.project.root.dataacces.Users;
import com.spring.project.root.repository.AppointmentsRepository;

@Service
public class AppointmentsService {
	
	@Autowired
	AppointmentsRepository appointmentsRepository;
	
	public List<Appointments> getList(){
		return this.appointmentsRepository.getAll();
	}
	
	public Appointments getById(int id) {
		return this.appointmentsRepository.getById(id);
	}

	
	public List<Appointments> getListByUser(Users iduser){
		return this.appointmentsRepository.getAllByUserId(iduser);
	}
	
	public List<Appointments> getListByUserCanceled(Users iduser){
		return this.appointmentsRepository.getAllByUserIdCanceled(iduser);
	}
	
	public List<Appointments> getListByUserMissed(Users iduser){
		return this.appointmentsRepository.getAllByUserIdMissed(iduser);
	}
	
	public List<Appointments> getListByUserDone(Users iduser){
		return this.appointmentsRepository.getAllByUserIdDone(iduser);
	}
	
	public void addAppointment(Appointments appointment){
		this.appointmentsRepository.save(appointment);
	}
	
	public void removeAppointment(Appointments appointment) {
		this.appointmentsRepository.delete(appointment);
	}
	
	public void updateAppointment(Appointments appointment) {
		this.appointmentsRepository.update(appointment);
	}

}
