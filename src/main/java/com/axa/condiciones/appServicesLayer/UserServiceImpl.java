package com.axa.condiciones.appServicesLayer;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axa.condiciones.businessLayer.UserBusiness;
import com.axa.condiciones.model.dto.MessageDTO;
import com.axa.condiciones.model.dto.UserDTO;
import com.axa.condiciones.model.entities.User;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserBusiness userBusiness;
	
	@Override
	public MessageDTO save(UserDTO userDTO) {
		MessageDTO reponse = null;
		User user = userBusiness.findByApUsernameAddress(userDTO.getAp(), userDTO.getUsername(), userDTO.getAddress());
		if(user == null) {	
			userBusiness.save(userDTO);
			reponse = new MessageDTO();
			reponse.setMsg("Sucess");
			
		}
		
		return reponse;
	}
	
	@Override
	public MessageDTO getAp(String ap) {
		MessageDTO response = null;
		MessageDTO responseBusiness = userBusiness.getAp(ap);
		if(responseBusiness != null) {
			response = new MessageDTO();
			response.setMsg(responseBusiness.getMsg());
		}
		
		return response;
	}

}
