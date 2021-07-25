package com.spring.project.root.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.project.root.dataacces.Users;
import com.spring.project.root.service.UsersService;


@Controller
@RequestMapping(path = "/login")
public class LoginController {

	@Autowired
	private UsersService usersService;

	private static final String ERROR_MESSAGE = "Invalid Credentials.";

	@RequestMapping(method = { RequestMethod.GET })
	public String doLogin(final HttpServletRequest request, final Model model) {
		model.addAttribute("User", new Users());
		return "login/login";
	}

	@RequestMapping(method = { RequestMethod.POST })
	public String doLogin(final RedirectAttributes redirectAttributes, final Model model, HttpSession session, final HttpServletRequest request,
			@ModelAttribute(value = "User") final Users User) {
		final String username = User.getUsername();
		final String password = User.getPassword();
		
		try {
			final Users user = this.usersService.getList().stream().map(u -> this.usersService.getByUsername(username)).findFirst().orElse(null);
			if (user != null) {
				
				final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
				final GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
				grantedAuthorities.add(authority);
				
				final Authentication auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
				SecurityContextHolder.getContext().setAuthentication(auth);
				
				session = request.getSession();
				
				session.setAttribute("User", user);
				
				if (user.getRole().equals("ROLE_ADMIN")) {
					return "redirect:/admin/dashboard";
				} 
				if (user.getRole().equals("ROLE_NURSE")) {
					return "redirect:/nurse/dashboard";
				} 
				else {
					return "redirect:/citizen/dashboard";
				}
			}
			model.addAttribute("loginError", LoginController.ERROR_MESSAGE);
		} catch (final Exception e) {
			model.addAttribute("loginError", LoginController.ERROR_MESSAGE);
			return "login/login";
		}
		return "login/login";
		
	}
	
	
}

