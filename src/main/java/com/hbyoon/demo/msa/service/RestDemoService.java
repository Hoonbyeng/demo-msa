package com.hbyoon.demo.msa.service;


import org.springframework.stereotype.Service;

import com.hbyoon.demo.common.rest.RestApi;
import com.hbyoon.demo.common.rest.RestResponse;
import com.hbyoon.demo.msa.dto.RestDemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestDemoService {


	public RestDemo getMabBiaMsa(String apiUrl) {
		log.debug("getMabBiaMsa(String apiUrl) apiUrl {} ", apiUrl);
		RestResponse<RestDemo> response = RestApi.client(apiUrl + "/biaMsA")
				.get(RestDemo.class);

		RestDemo demo = response.getBody();
		demo.setId(demo.getId() + "(bia msA)");
		demo.setName(demo.getName() + "(bia msA)");
		demo.setDescription(demo.getDescription() + "(bia msA)");
		
		log.debug("Description: {}", demo.getDescription());
		
		return demo;
	}

	public RestDemo getFromApi(String apiUrl) {
		RestResponse<RestDemo> response = RestApi.client(apiUrl + "/{id}")
				.setHeader("Accept", "application/json")
				.queryParam("test0", "test0")
				.queryParam("test1", "test1")
				.uriVariable("id", "12345")
				.get(RestDemo.class);

		RestDemo demo = response.getBody();
		
		log.debug("Description: {}", demo.getDescription());
		
		return demo;
	}
	

	public RestDemo putDemoDto(RestDemo dto) {
		RestDemo request = new RestDemo();
		request.setId("12345");
		request.setName("tester2");
		request.setDescription("this is update test.");
		
		RestResponse<RestDemo> response = RestApi.client("http://localhost:9090/demo/put")
				.post(request, RestDemo.class);
		
		RestDemo demo = response.getBody();
		
		log.debug("Description: {}", demo.getDescription());
		
		return demo;
	}


}
