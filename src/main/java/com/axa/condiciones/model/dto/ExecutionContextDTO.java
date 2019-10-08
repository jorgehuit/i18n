package com.axa.condiciones.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionContextDTO implements Serializable{
	private static final long serialVersionUID = -4570987439534732055L;

	public String username;
	public String opName;


}