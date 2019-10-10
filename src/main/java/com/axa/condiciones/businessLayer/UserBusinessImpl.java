package com.axa.condiciones.businessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axa.condiciones.common.GenericException;
import com.axa.condiciones.model.dto.ExecutionContextDTO;
import com.axa.condiciones.model.dto.MessageDTO;
import com.axa.condiciones.model.dto.UserDTO;
import com.axa.condiciones.model.entities.UserExa;
import com.axa.condiciones.persistenceLayer.UserRepository;

@Service
public class UserBusinessImpl implements UserBusiness {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public MessageDTO getAp(String ap) {
		MessageDTO messageDTO = null;
		UserExa user = userRepository.findByAp(ap);
		if(user != null) {
			messageDTO = new MessageDTO();
			messageDTO.setDataApi(user.getUsername());
		}
		return messageDTO;
	}

	@Override
	public UserExa findByApUsernameAddress(String ap, String username, String address) {
		return userRepository.findByApUsernameAddress(ap, username, address);
	}

	@Override
	public MessageDTO save(UserDTO userDTO) {
		MessageDTO response = null;
		UserExa user = userRepository.save(createEntity(userDTO));
		if(user != null) {
			response = new MessageDTO();
			response.setDataApi("Created");
		}
		
		return response;
	}
	
	@Override
	public UserDTO getInfoUser(String app, ExecutionContextDTO exc) throws GenericException{
		UserExa findByAp = userRepository.findByAp(app);
		if(findByAp == null) {
			throw new GenericException("Id not fouund");
		}
		return mapFromUserEntityToUserDTO(findByAp);
	}
	
	private UserDTO mapFromUserEntityToUserDTO(UserExa findByAp) {
		UserDTO userDTO = new UserDTO();
		userDTO.setAddress(findByAp.getAddress());
		userDTO.setAp(findByAp.getAp());
		userDTO.setEmail(findByAp.getEmail());
		userDTO.setUsername(findByAp.getUsername());
		
		return userDTO;
	}

	private UserExa createEntity(UserDTO userDTO) {
		UserExa userEntity = new UserExa();
		userEntity.setAddress(userDTO.getAddress());
		userEntity.setAp(userDTO.getAp());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setUsername(userDTO.getUsername());
		return userEntity;
	}

}
