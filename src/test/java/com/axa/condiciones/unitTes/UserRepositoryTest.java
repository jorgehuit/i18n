package com.axa.condiciones.unitTes;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.axa.condiciones.model.dto.ExecutionContextDTO;
import com.axa.condiciones.model.entities.UserExa;

import lombok.extern.log4j.Log4j;

@Log4j
public class UserRepositoryTest {
	Map<Long, UserExa> mapSaveUser = new HashMap<Long, UserExa>(); 
	
	@Test
	public void saveUserEntity() {
		log.info("Entramos al test saveUserEntity");
		UserExa user = new UserExa();
		user.setAddress("address");
		user.setAp("App");
		user.setEmail("email");
		user.setUsername("username");
		user.setId(10L);
		
		mapSaveUser.put(1L, user);
		
		Assert.assertNotNull(mapSaveUser);
	}
	
	@Test
	public void findByAp() {
		
		log.info("Entramos al test findByAp");
		UserExa user = new UserExa();
		user.setAddress("address");
		user.setAp("App");
		user.setEmail("email");
		user.setUsername("username");
		user.setId(10L);
		
		mapSaveUser.put(1L, user);
		
		Assert.assertNotNull(mapSaveUser);
		
		UserExa userRecovery = mapSaveUser.get(1L);
		
		Assert.assertNotNull(userRecovery);
		Assert.assertNotNull(userRecovery.getAp());
		Assert.assertNotNull(userRecovery.getAddress());
		Assert.assertNotNull(userRecovery.getEmail());
		Assert.assertNotNull(userRecovery.getUsername());
		Assert.assertNotNull(userRecovery.getId());
		
	}
	
	@Test
	public void modifyUserEntity() {
		log.info("Entramos al test modifyUserEntity");
		UserExa user = new UserExa();
		user.setAddress("address");
		user.setAp("App");
		user.setEmail("email");
		user.setUsername("username");
		user.setId(10L);
		
		mapSaveUser.put(1L, user);
		
		Assert.assertNotNull(mapSaveUser);
		
		UserExa userRecovery = mapSaveUser.get(1L);
		
		userRecovery.setAddress("new address");
		userRecovery.setAp("new App");
		
		mapSaveUser.put(1L, userRecovery);
		
		Assert.assertNotNull(userRecovery);
		Assert.assertNotEquals("address", userRecovery.getAddress());
		Assert.assertNotEquals("App", userRecovery.getAp());
		
	}
	
	@Test
	public void deleteUserEntity() {
		log.info("Entramos al test deleteUserEntity");
		UserExa user = new UserExa();
		user.setAddress("address");
		user.setAp("App");
		user.setEmail("email");
		user.setUsername("username");
		user.setId(10L);
		
		mapSaveUser.put(1L, user);
		
		Assert.assertNotNull(mapSaveUser);
		
		UserExa userRecovery = mapSaveUser.get(1L);
		Assert.assertNotNull(userRecovery);
		
		mapSaveUser.remove(1L);
		
		UserExa userRecovery2 = mapSaveUser.get(1L);
		
		Assert.assertNull(userRecovery2);
		
		
	}
	
	@Test
	public void delete() {
		ExecutionContextDTO contextDTO = new ExecutionContextDTO();
		contextDTO.setOpName("nombre de operacion");
		contextDTO.setUsername("nombre de usuario");
		String executionContextLog = this.getExecutionContextLog(contextDTO);
		log.info(executionContextLog);
	}
	
	public String getExecutionContextLog(ExecutionContextDTO exc) {
		return String.format("transID: %s, userName: %s, opName: %s ", 
				UUID.randomUUID(), 
				exc.getUsername(), 
				exc.getOpName());
	}

}
