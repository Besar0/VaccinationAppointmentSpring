package com.spring.project.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.project.root.dataacces.Users;
import com.spring.project.root.service.UsersService;

@Controller
public class AdminController {
	
	@Autowired
	UsersService usersService;
	
	@RequestMapping(value = "admin/dashboard", method = RequestMethod.GET)
	public String doGet(final Model model) {
		Users user = this.usersService.getByUsername(loggedUser());
		model.addAttribute("User", user);
		return "admin/dashboard";
	}
	
	private String loggedUser() {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 Object principal = authentication.getPrincipal();
		 return principal.toString();
	}

}
