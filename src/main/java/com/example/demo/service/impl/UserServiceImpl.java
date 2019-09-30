package com.example.demo.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.respository.UserRepository;
import com.example.demo.service.UserService;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(UserDTO userDTO) {
		User user = null;
		User findByApUsernameAddress = 
				userRepository.findByApUsernameAddress(userDTO.getAp(), userDTO.getUsername(), userDTO.getAddress());
		if(findByApUsernameAddress == null) {	
			user = userRepository.save(createEntity(userDTO));
		}
		
		return user;
	}
	
	@Override
	public User getAp(String ap) {
		return userRepository.findByAp(ap);
	}

	private User createEntity(UserDTO userDTO) {
		User userEntity = new User();
		userEntity.setAddress(userDTO.getAddress());
		userEntity.setAp(userDTO.getAp());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setUsername(userDTO.getUsername());
		return userEntity;
	}

	

}
