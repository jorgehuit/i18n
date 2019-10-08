package com.axa.condiciones.appServicesLayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionContext {
	public String username;
	public String opName;


}