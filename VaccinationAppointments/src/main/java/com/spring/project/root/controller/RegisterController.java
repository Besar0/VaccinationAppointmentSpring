package com.spring.project.root.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.project.root.dataacces.Citizens;
import com.spring.project.root.dataacces.Users;
import com.spring.project.root.service.CitizenService;
import com.spring.project.root.service.UsersService;

@Controller
public class RegisterController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	CitizenService citizenService;
	
	@RequestMapping(value = "register/citizen", method = RequestMethod.GET)
	public String doGetCitizen(final Model model) {
		model.addAttribute("user", new Users());
		return "register/citizen";
	}
	
	@RequestMapping(value = "register/foreigner", method = RequestMethod.GET)
	public String doGetForeigner(final Model model) {
		model.addAttribute("user", new Users());
		model.addAttribute("citizen", new Citizens());
		return "register/foreigner";
	}
	
	@RequestMapping(path = "/addUserCitizen", method = { RequestMethod.POST })
	public String doAddNewCitizen(@ModelAttribute Users user, Model model) {
		
		
		List<Citizens> AllCitizens = citizenService.getList();
		for(Citizens c : AllCitizens) {
			if(Integer.toString(c.getSsn()).equals(user.getUsername())) {
				user.setRole("ROLE_CITIZEN");
				user.setIdcitizen(c);
				usersService.addUser(user);
				return "redirect:login";
			}
		}
		return "redirect:register/citizen";
	}
	
	@RequestMapping(path = "/addUserForeigner", method = { RequestMethod.POST })
	public String doAddNewForeigner(@ModelAttribute Users user, @ModelAttribute Citizens citizen, 
			@RequestParam(name="date")String date, @RequestParam(name="sex")String sex, Model model) throws ParseException {
		
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");  
		Date date1 = formatter.parse(date);
		citizen.setDateOfBirth(date1);
		citizen.setSex(sex);
		Citizens addedCitizen = citizenService.addCitizen(citizen);
		
		user.setIdcitizen(addedCitizen);
		user.setRole("ROLE_CITIZEN");
		usersService.addUser(user);
		return "redirect:login";
	}

	
}
