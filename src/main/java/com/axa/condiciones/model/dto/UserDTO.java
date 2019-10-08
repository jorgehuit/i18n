package com.axa.condiciones.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable{
	private static final long serialVersionUID = 3834789882162953088L;

	private String ap;
	private String username;
	private String address;
	private String email;
}