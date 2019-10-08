package com.axa.condiciones.apiIntegrationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.axa.condiciones.appServicesLayer.UserService;
import com.axa.condiciones.common.GenericException;
import com.axa.condiciones.common.UtilService;
import com.axa.condiciones.model.dto.ExecutionContextDTO;
import com.axa.condiciones.model.dto.MessageDTO;
import com.axa.condiciones.model.dto.UserDTO;
import com.axa.condiciones.retry.UserRetry;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class UserController {
	
	private static final String DEMOUSER = "DEMOUSER";

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRetry userRetry;
	
	@Autowired
	private BuildProperties buildProperties; 
	
	//--------------------Devuelve versión deployada en servidor---------------------------------//
	@GetMapping(value = "/getInfoVersion")
	public ResponseEntity<MessageDTO> getInfoVersion(){
		return new ResponseEntity<MessageDTO>(new MessageDTO(buildProperties.getVersion()), HttpStatus.OK);
	}

	//--------------------Consume un servicio REST y lo implementa con Spring Retry---------------------------------//
	@GetMapping(value = "/getTryService")
	public ResponseEntity<MessageDTO> getTryService(){
		log.info("Entra a /getTryService ");
		MessageDTO messageDTO = new MessageDTO();
		ExecutionContextDTO executionContext = new ExecutionContextDTO(DEMOUSER,"/getTryService");
		messageDTO.setDataApi(userRetry.getRemoteBackendResponse(executionContext) + ", ahora ya consumido desde Spring Retry...");
		return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);
	}
	
	//--------------------Devuelve mensaje de internacionalización---------------------------------//
	@GetMapping(value = "/api")
	public ResponseEntity<MessageDTO> getMessage(@RequestParam(value = "ap", required = true) String ap) {
		log.info("Entra a obtener internacionalización.");
		MessageDTO responseService = userService.getAp(ap);
		String message = null;
		if(responseService != null) {
			message = messageSource.getMessage("welcome.user", new String[]{responseService.getDataApi()}, LocaleContextHolder.getLocale());
			
		}else {
			message = messageSource.getMessage("user.not.found", null, LocaleContextHolder.getLocale());
			return new ResponseEntity<MessageDTO>(new MessageDTO(message), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MessageDTO>(new MessageDTO(message), HttpStatus.OK);
	}
	
	//--------------------Inserta un usuario---------------------------------//
	@PostMapping(value = "/insertUser")
	public ResponseEntity<MessageDTO> inserUser(@RequestBody UserDTO userDTO) {
		String msg = null;
		MessageDTO responseService = userService.save(userDTO);
		if(responseService != null) {
			msg = messageSource.getMessage("user.add", null, LocaleContextHolder.getLocale());
			
		}else {
			msg = messageSource.getMessage("user.not.add", null, LocaleContextHolder.getLocale());
			return new ResponseEntity<MessageDTO>(new MessageDTO(msg), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<MessageDTO>(new MessageDTO(msg), HttpStatus.CREATED);
	}
	
	//--------------------Obtiene información de un usuario---------------------------------//
	@GetMapping(value = "/getInfoUser/{app}")
	public ResponseEntity<UserDTO> getInfoUser(@PathVariable("app") String app){
		UserDTO userDto = null;
		ExecutionContextDTO executionContext = new ExecutionContextDTO(DEMOUSER,"/getInfoUser/{app}");
		try {
			userDto = userService.getInfoUser(app, executionContext);
		} catch (GenericException e) {
			log.error(UtilService.getExecutionContextLog( executionContext ), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Incorrect.", e);
		}
		return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
	}
	
}
