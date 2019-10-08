package com.axa.condiciones.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import com.axa.condiciones.common.GenericException;
import com.axa.condiciones.model.dto.ExecutionContextDTO;

public interface UserRetry {
	@Retryable(
			value = {GenericException.class }, 
			maxAttempts = 3, 
			backoff = @Backoff(delay = 1000))
	public String getRemoteBackendResponse(ExecutionContextDTO executionContext) throws GenericException;
	
	@Recover
	public String recover(GenericException ge);

}
