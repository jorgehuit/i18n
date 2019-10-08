package com.axa.condiciones.common;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.axa.condiciones.model.dto.ExecutionContextDTO;

@Service
public class UtilService {

	public static String getExecutionContextLog(ExecutionContextDTO exc) {
		return String.format("transID: %s, userName: %s, opName: %s ", 
				UUID.randomUUID(), 
				exc.getUsername(), 
				exc.getOpName());
	}
}
