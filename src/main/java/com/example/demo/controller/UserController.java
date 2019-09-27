package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Message;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/api")
	public Message getMessage(@RequestParam String ap) {
		User userEntity = userService.getAp(ap);
		String message = null;
		if(userEntity != null) {
			String username = userEntity.getUsername();
			message = messageSource.getMessage("welcome.user", new String[]{username}, LocaleContextHolder.getLocale());
			
		}else {
			message = messageSource.getMessage("user.not.found", null, LocaleContextHolder.getLocale());
		}
		return new Message(message);
	}
	
	@PostMapping(value = "/insertUser")
	public Message inserUser(@RequestBody UserDTO userDTO) {
		String msg = null;
		User userEntity = userService.save(userDTO);
		if(userEntity != null) {
			msg = messageSource.getMessage("user.add", null, LocaleContextHolder.getLocale());
		}else {
			msg = messageSource.getMessage("user.not.add", null, LocaleContextHolder.getLocale());
			
		}
		return new Message(msg);
	}

}
