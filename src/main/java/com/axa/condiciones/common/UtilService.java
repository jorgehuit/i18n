package com.axa.condiciones.common;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.axa.condiciones.appServicesLayer.ExecutionContext;

@Service
public class UtilService {

	public static String getExecutionContextLog(ExecutionContext exc) {
		return String.format("transID:{0}, userName:{1}, opName:{2} ", UUID.randomUUID(), exc.getUsername(), exc.getOpName());
	}
}
