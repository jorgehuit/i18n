package com.axa.condiciones.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO implements Serializable{
	private static final long serialVersionUID = -5161335765525832614L;

	private String dataApi;
	
}
