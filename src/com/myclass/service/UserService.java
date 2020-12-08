package com.myclass.service;

import java.util.ArrayList;
import java.util.List;


import com.myclass.dto.UserDto;
import com.myclass.entity.Role;
import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;

public class UserService {

	private UserRepository userRepository = null;
	private RoleRepository roleRepository = null;
	
	public UserService() {
		userRepository = new UserRepository();
		roleRepository = new RoleRepository();
	}
	
	public List<UserDto> getAll() {
		List<UserDto> userDtos = new ArrayList<UserDto>();
		List<User> entities = userRepository.findAll();
		for (User user : entities) {
			
			Role role = roleRepository.findById(user.getRoleId());
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setEmail(user.getEmail());
			dto.setFullname(user.getFullname());
			dto.setRoleDec(role.getDescription());
			userDtos.add(dto);
		}
		return userDtos;
	}
	
	public List<UserDto> getAllUserRole(){
		return userRepository.findAllUserRole();
	}
	
	public UserDto checkLogin(String email, String pass) {
		User user = userRepository.findByEmail(email);
		if(user == null) return null;
		
		Role role = roleRepository.findById(user.getRoleId());
		
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setFullname(user.getFullname());
		dto.setRoleName(role.getName());
		
		return dto;
		
	}
}
