package com.axa.condiciones.retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.axa.condiciones.client.Aggregator;
import com.axa.condiciones.common.GenericException;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UserRetryImpl implements UserRetry {
	
	@Autowired
	private Aggregator aggregator;

	@Override
	public String getRemoteBackendResponse() throws GenericException{
		log.info("Entra a getRemoteBackendResponse");
		String dataApi = null;
		try {
			dataApi = aggregator.getHello().getDataApi();
			
		} catch (Exception e) {
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
