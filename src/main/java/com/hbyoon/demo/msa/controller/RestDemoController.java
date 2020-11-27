package com.hbyoon.demo.msa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbyoon.demo.msa.dto.RestDemo;
import com.hbyoon.demo.msa.service.RestDemoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/msa")
public class RestDemoController {

	@Value("${demo.api.msa}")
	private String msaUrl;

	@Value("${demo.api.msb}")
	private String msbUrl;

	@Autowired
	private RestDemoService restDemoService;

	@GetMapping(value={"", "/{id}"})
	public RestDemo hello(@PathVariable(name="id", required=false) String id) {
		RestDemo d = new RestDemo();
		d.setId(id);
		d.setName("msA");
		d.setDescription("this is msa.");
		log.debug("I am msA : got {}, dending {}", id, d);
		return d;
	}

	@GetMapping("/getMsb")
	public RestDemo getMsb() {
		RestDemo d = restDemoService.getMabBiaMsa(msbUrl);

		log.debug("I am msA : sending {}", d);
		return d;
	}

	@GetMapping("error/{id}")
	public RestDemo error(String id) {
		throw new RuntimeException("ERROR");
	}

	@PostMapping("/put")
	public RestDemo update(@RequestBody RestDemo demo) {
		RestDemo d = new RestDemo();
		d.setId(demo.getId());
		d.setName(demo.getName());
		d.setDescription("[UPDATED] result from msa.");
		return d;
	}
	
	@GetMapping("/waiting")
	public RestDemo waiting() {
		try {
			Thread.sleep(2000L);
		} catch (Exception e) {
			// TODO: handle exception
		}
		RestDemo d = new RestDemo();
		d.setId("long waiting data");
		d.setName("");
		d.setDescription("");
		return d;
	}
	
}
