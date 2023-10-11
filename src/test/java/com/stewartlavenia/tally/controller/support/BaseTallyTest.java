package com.stewartlavenia.tally.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import lombok.Getter;

public class BaseTallyTest {

	@LocalServerPort
	private int serverPort;
	
	@Autowired
	@Getter
	private TestRestTemplate restTemplate;
	
	/**
	 * 
	 * @return
	 */
	protected String getBaseUriForCustomers() {
		return String.format("http://localhost:%d/customers", serverPort);
	}
	/**
	 * 
	 * @return
	 */
	protected String getBaseUriForUsers() {
		return String.format("http://localhost:%d/users", serverPort);
	}
}
