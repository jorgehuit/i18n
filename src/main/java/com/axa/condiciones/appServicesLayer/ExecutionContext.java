package com.axa.condiciones.appServicesLayer;

import java.util.UUID;

public class ExecutionContext {
	public UUID transID;
	public String username;
	public String opName;

	public ExecutionContext(UUID transID, String username, String opName) {
		this.transID = transID;
		this.username = username;
		this.opName = opName;
	}
}