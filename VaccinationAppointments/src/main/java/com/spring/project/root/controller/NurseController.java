package com.spring.project.root.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.project.root.dataacces.Appointments;
import com.spring.project.root.dataacces.Users;
import com.spring.project.root.service.AppointmentsService;
import com.spring.project.root.service.UsersService;

@Controller
public class NurseController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	AppointmentsService appointmentService;
	
	@RequestMapping(value = "nurse/dashboard", method = RequestMethod.GET)
	public String doGet(final Model model) {
		Users user = this.usersService.getByUsername(loggedUser());
		model.addAttribute("User", user);
		
		List<Appointments> list = appointmentService.getList();
		if(list != null) {
			list = list.stream()
					.filter(i -> i.getCanceled() == (short)0)
					.filter(i -> i.getMissed() == (short)0)
					.filter(i -> i.getDone() == (short)0)
					.collect(Collectors.toList());
			model.addAttribute("appointments", list);
		}
		return "nurse/dashboard";
	}
	
	@RequestMapping(path = "/doneAppointment", method = { RequestMethod.GET })
	public String doDoneAppointment(@RequestParam(name="idappointment")int idappointment, Model model) {
		Appointments appointment =  appointmentService.getById(idappointment);
		appointment.setDone((short) 1);
		appointmentService.updateAppointment( appointment );
		
		if(appointment.getIdvaccine().getDoses() == 1) {
			Users user = usersService.getByid(appointment.getIduser().getIduser());
			user.setVaccinated((short) 1);
			usersService.updateUser(user);
		}
		
		List<Appointments> done = appointmentService.getListByUser(appointment.getIduser());
		if(done != null) {
			done = done.stream()
					.filter(i -> i.getDone() == (short)1)
					.collect(Collectors.toList());
		}

		if(appointment.getIdvaccine().getDoses() == 2 && done.size()==1) {
			Appointments newAppointment = new Appointments();
			newAppointment.setIduser( appointment.getIduser() );
			newAppointment.setIdvaccine( appointment.getIdvaccine() );
			newAppointment.setIdcenter( appointment.getIdcenter() );
			
			Date date = appointment.getDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, 30);
			Date newDate = cal.getTime();
			
			newAppointment.setDate( newDate );
			newAppointment.setTime( appointment.getTime() );
			
			appointmentService.addAppointment(newAppointment);
		}
		
		if(appointment.getIdvaccine().getDoses() == 2 && done.size()==2) {
			Users user = usersService.getByid(appointment.getIduser().getIduser());
			user.setVaccinated((short) 1);
			usersService.updateUser(user);
		}

		return "redirect:nurse/dashboard";
	}
	
	@RequestMapping(path = "/missedAppointment", method = { RequestMethod.GET })
	public String doUpdateAppointment(@RequestParam(name="idappointment")int idappointment, Model model) {
		Appointments appointment =  appointmentService.getById(idappointment);
		appointment.setMissed((short) 1);
		appointmentService.updateAppointment( appointment);
		return "redirect:nurse/dashboard";
	}
	
	private String loggedUser() {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 Object principal = authentication.getPrincipal();
		 return principal.toString();
	}
	
}
