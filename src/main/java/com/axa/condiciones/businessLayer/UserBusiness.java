package com.axa.condiciones.businessLayer;

import com.axa.condiciones.common.GenericException;
import com.axa.condiciones.model.dto.ExecutionContextDTO;
import com.axa.condiciones.model.dto.MessageDTO;
import com.axa.condiciones.model.dto.UserDTO;
import com.axa.condiciones.model.entities.UserExa;

public interface UserBusiness {
	public MessageDTO getAp(String ap);

	public UserExa findByApUsernameAddress(String ap, String username, String address);

	public MessageDTO save(UserDTO userDTO);

	public UserDTO getInfoUser(String app, ExecutionContextDTO exc) throws GenericException;
	
}
