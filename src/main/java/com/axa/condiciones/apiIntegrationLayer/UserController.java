package com.axa.condiciones.apiIntegrationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axa.condiciones.appServicesLayer.UserService;
import com.axa.condiciones.model.dto.MessageDTO;
import com.axa.condiciones.model.dto.UserDTO;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class UserController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BuildProperties buildProperties; 
	
	@GetMapping(value = "/getInfoVersion")
	public ResponseEntity<MessageDTO> getInfoVersion(){
		return new ResponseEntity<MessageDTO>(new MessageDTO(buildProperties.getVersion()), HttpStatus.OK);
	}
	
	@GetMapping(value = "/api")
	public ResponseEntity<MessageDTO> getMessage(@RequestParam(value = "ap", required = true) String ap) {
		log.info("Entra a obtener internacionalización.");
		MessageDTO responseService = userService.getAp(ap);
		String message = null;
		if(responseService != null) {
			message = messageSource.getMessage("welcome.user", new String[]{responseService.getMsg()}, LocaleContextHolder.getLocale());
			
		}else {
			message = messageSource.getMessage("user.not.found", null, LocaleContextHolder.getLocale());
			return new ResponseEntity<MessageDTO>(new MessageDTO(message), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MessageDTO>(new MessageDTO(message), HttpStatus.OK);
	}
	
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

}
