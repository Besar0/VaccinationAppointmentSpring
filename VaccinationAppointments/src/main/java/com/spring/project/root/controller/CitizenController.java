package com.spring.project.root.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.project.root.dataacces.Appointments;
import com.spring.project.root.dataacces.Users;
import com.spring.project.root.dataacces.Vaccines;
import com.spring.project.root.service.AppointmentsService;
import com.spring.project.root.service.CentersService;
import com.spring.project.root.service.UsersService;
import com.spring.project.root.service.VaccineService;

@Controller
public class CitizenController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	AppointmentsService appointmentService;
	
	@Autowired
	VaccineService vaccineService;
	
	@Autowired
	CentersService centerService;
	
	
	@RequestMapping(value = "citizen/dashboard", method = RequestMethod.GET)
	public String doGet(final Model model) {
		Users user = this.usersService.getByUsername(loggedUser());
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();
		LocalDate currentDate = LocalDate.parse(dtf.format(now));
		
		int age = Period.between(user.getIdcitizen().getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), currentDate).getYears();
		List<Vaccines> vaccines = vaccineService.getListAvailable();
		vaccines = vaccines.stream().filter(i -> i.getMinAge() <= age).collect(Collectors.toList());
		
		model.addAttribute("User", user);
		model.addAttribute("appointment", new Appointments());
		model.addAttribute("vaccines", vaccines);
		model.addAttribute("centers", centerService.getList());
		model.addAttribute("today", dtf.format(now));
		
		List<Appointments> pending = appointmentService.getListByUser(user);
		if(pending != null) {
			pending = pending.stream()
					.filter(i -> i.getCanceled() == (short)0)
					.filter(i -> i.getMissed() == (short)0)
					.filter(i -> i.getDone() == (short)0)
					.collect(Collectors.toList());
			model.addAttribute("pending", pending);
		}
		
		
		return "citizen/dashboard";
	}
	
	@RequestMapping(path = "/addAppointment", method = { RequestMethod.POST })
	public String doAddAppointment(@ModelAttribute Appointments appointment, @RequestParam(name="date")String date, Model model) throws ParseException {
		appointment.setIduser(this.usersService.getByUsername(loggedUser()));
		
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");  
		Date date1 = formatter.parse(date);
		
		appointment.setDate(date1);
		
		appointmentService.addAppointment(appointment);
		return "redirect:citizen/dashboard";
	}
	
	@RequestMapping(path = "/removeAppointment", method = { RequestMethod.GET })
	public String doRemoveAppointment(@RequestParam(name="idappointment")int idappointment, Model model) {
		appointmentService.removeAppointment( appointmentService.getById(idappointment) );
		return "redirect:citizen/dashboard";
	}

	@RequestMapping(path = "/cancelAppointment", method = { RequestMethod.GET })
	public String doUpdateAppointment(@RequestParam(name="idappointment")int idappointment, Model model) {
		Appointments appointment =  appointmentService.getById(idappointment);
		appointment.setCanceled((short) 1);
		appointmentService.updateAppointment( appointment );
		return "redirect:citizen/dashboard";
	}
	
	
	private String loggedUser() {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 Object principal = authentication.getPrincipal();
		 return principal.toString();
	 }

	
}
