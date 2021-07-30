package com.spring.project.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.root.dataacces.Users;
import com.spring.project.root.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public List<Users> getList(){
		return this.usersRepository.getAll();
	}
	
	public Users getByUsername(String username) {
		return this.usersRepository.getUserByUsername(username);
	}
	
	public Users getByid(int iduser) {
		return this.usersRepository.getById(iduser);
	}
	
	public void addUser(Users user){
		this.usersRepository.save(user);
	}
	
	public void updateUser(Users user){
		this.usersRepository.update(user);
	}
	
}
