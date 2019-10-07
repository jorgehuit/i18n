package com.axa.condiciones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import com.axa.condiciones.model.dto.DataRestClient;

@FeignClient(name = "Aggregator", url = "${feign.url}")
public interface Aggregator {
	
	@GetMapping(value="/api", consumes=MediaType.APPLICATION_JSON_VALUE)
	DataRestClient getHello();
}
