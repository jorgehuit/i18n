package com.axa.condiciones.businessLayer;

import com.axa.condiciones.common.GenericException;
import com.axa.condiciones.model.dto.MessageDTO;
import com.axa.condiciones.model.dto.UserDTO;
import com.axa.condiciones.model.entities.User;

public interface UserBusiness {
	public MessageDTO getAp(String ap);

	public User findByApUsernameAddress(String ap, String username, String address);

	public MessageDTO save(UserDTO userDTO);

	public UserDTO getInfoUser(String app) throws GenericException;
}
