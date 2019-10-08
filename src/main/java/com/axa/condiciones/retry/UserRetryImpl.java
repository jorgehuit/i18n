package com.axa.condiciones.retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.axa.condiciones.client.BusinessRule;
import com.axa.condiciones.common.GenericException;
import com.axa.condiciones.common.UtilService;
import com.axa.condiciones.model.dto.ExecutionContextDTO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UserRetryImpl implements UserRetry {
	
	@Autowired
	private BusinessRule aggregator;

	@Override
	public String getRemoteBackendResponse(ExecutionContextDTO executionContext) throws GenericException{
		log.info("Entra a getRemoteBackendResponse");
		String dataApi = null;
		try {
			dataApi = aggregator.getHello().getDataRemote();
			
		} catch (Exception e) {
			log.error(UtilService.getExecutionContextLog( executionContext ), e);
			throw new GenericException("GenericException in getRemoteBackendResponse ");
		}
		
		return dataApi;
	}

	@Override
	public String recover(GenericException e) {
		log.info("TODOS LOS INTENTOS FUERON REALIZADOS, PERO EL SERVICIO NO CONTESTA");
		throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT, "Server not response 3 times", e);
		
	}

}
