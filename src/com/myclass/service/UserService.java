package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

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
		// Lấy dữ liệu từ Repository (entity)
		List<User> entities = userRepository.findAll();
		// Đổ dữ liệu từ ENTITY vào DTO
		for (User user : entities) {
			
			Role role = roleRepository.findById(user.getRoleId());
			
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setEmail(user.getEmail());
			dto.setFullname(user.getFullname());
			dto.setRoleDec(role.getDescription());
			userDtos.add(dto);
		}
		// Trả về DTO
		return userDtos;
	}
	
	public List<UserDto> getAllUserRole(){
		return userRepository.findAllUserRole();
	}
	
	public UserDto checkLogin(String email, String pass) {
		User user = userRepository.findByEmail(email);
		if(user == null) return null;
		
//		boolean result = BCrypt.checkpw(pass, user.getPassword());
//		if(!result) return null;
		
		Role role = roleRepository.findById(user.getRoleId());
		
		UserDto dto = new UserDto();
		dto.setEmail(user.getEmail());
		dto.setFullname(user.getFullname());
		dto.setRoleName(role.getName());
		
		return dto;
		
	}
}
