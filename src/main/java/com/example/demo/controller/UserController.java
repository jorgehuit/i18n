package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Message;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class UserController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/api")
	public ResponseEntity<Message> getMessage(@RequestParam String ap) {
		log.info("Entra a obtener internacionalización.");
		User userEntity = userService.getAp(ap);
		String message = null;
		if(userEntity != null) {
			message = messageSource.getMessage("welcome.user", new String[]{userEntity.getUsername()}, LocaleContextHolder.getLocale());
			
		}else {
			message = messageSource.getMessage("user.not.found", null, LocaleContextHolder.getLocale());
			return new ResponseEntity<Message>(new Message(message), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Message>(new Message(message), HttpStatus.OK);
	}
	
	@PostMapping(value = "/insertUser")
	public ResponseEntity<Message> inserUser(@RequestBody UserDTO userDTO) {
		String msg = null;
		User userEntity = userService.save(userDTO);
		if(userEntity != null) {
			msg = messageSource.getMessage("user.add", null, LocaleContextHolder.getLocale());
			
		}else {
			msg = messageSource.getMessage("user.not.add", null, LocaleContextHolder.getLocale());
			return new ResponseEntity<Message>(new Message(msg), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Message>(new Message(msg), HttpStatus.CREATED);
	}

}
