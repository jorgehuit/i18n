package com.axa.condiciones.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataRestClientDTO implements Serializable{
	private static final long serialVersionUID = 4575676598439181977L;

	private String dataRemote;
}
