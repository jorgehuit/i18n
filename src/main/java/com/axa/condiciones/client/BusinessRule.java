package com.axa.condiciones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import com.axa.condiciones.common.GenericException;
import com.axa.condiciones.model.dto.DataRestClientDTO;

@FeignClient(name = "businessRule", url = "${feign.url}")
public interface BusinessRule{
	
	@GetMapping(value="/api", consumes=MediaType.APPLICATION_JSON_VALUE)
	DataRestClientDTO getHello() throws GenericException;
}
