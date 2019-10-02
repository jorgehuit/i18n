package com.axa.condiciones.businessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axa.condiciones.model.dto.MessageDTO;
import com.axa.condiciones.model.dto.UserDTO;
import com.axa.condiciones.model.entities.User;
import com.axa.condiciones.persistenceLayer.UserRepository;

@Service
public class UserBusinessImpl implements UserBusiness {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public MessageDTO getAp(String ap) {
		MessageDTO messageDTO = null;
		User user = userRepository.findByAp(ap);
		if(user != null) {
			messageDTO = new MessageDTO();
			messageDTO.setMsg(user.getUsername());
		}
		return messageDTO;
	}

	@Override
	public User findByApUsernameAddress(String ap, String username, String address) {
		return userRepository.findByApUsernameAddress(ap, username, address);
	}

	@Override
	public MessageDTO save(UserDTO userDTO) {
		MessageDTO response = null;
		User user = userRepository.save(createEntity(userDTO));
		if(user != null) {
			response = new MessageDTO();
			response.setMsg("Created");
		}
		
		return response;
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
