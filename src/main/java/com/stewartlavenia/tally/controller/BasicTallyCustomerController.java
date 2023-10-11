package com.stewartlavenia.tally.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.stewartlavenia.tally.entity.Customer;
import com.stewartlavenia.tally.entity.CustomerRequest;
import com.stewartlavenia.tally.service.TallyCustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicTallyCustomerController implements TallyCustomerController {

	@Autowired
	private TallyCustomerService tallyCustomerService; 
	
	@Override
	public Customer createCustomer(CustomerRequest customerRequest) {
		log.debug("Customer={}", customerRequest);
		return tallyCustomerService.createCustomer(customerRequest);
	}
	
	

}
