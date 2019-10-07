package com.axa.condiciones.appServicesLayer;

import com.axa.condiciones.common.GenericException;
import com.axa.condiciones.model.dto.DataRestClient;
import com.axa.condiciones.model.dto.MessageDTO;
import com.axa.condiciones.model.dto.UserDTO;

public interface UserService {
	MessageDTO save(UserDTO userDTO);

	MessageDTO getAp(String ap);

	UserDTO getInfoUser(String app, ExecutionContext exc) throws GenericException;
	
	DataRestClient getRemoteService();
}
