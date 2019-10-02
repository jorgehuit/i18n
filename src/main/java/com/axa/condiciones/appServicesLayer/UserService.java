package com.axa.condiciones.appServicesLayer;

import com.axa.condiciones.model.dto.MessageDTO;
import com.axa.condiciones.model.dto.UserDTO;
import com.axa.condiciones.model.entities.User;

public interface UserService {
	MessageDTO save(UserDTO userDTO);

	MessageDTO getAp(String ap);
}
