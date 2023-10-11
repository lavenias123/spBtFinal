package com.stewartlavenia.tally.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stewartlavenia.tally.dao.TallyCustomerDao;
import com.stewartlavenia.tally.entity.Customer;
import com.stewartlavenia.tally.entity.CustomerRequest;
import com.stewartlavenia.tally.entity.Users;

@Service
public class DefaultTallyCustomerService implements TallyCustomerService {

	@Autowired
	private TallyCustomerDao tallyCustomerDao;
	
	@Transactional
	@Override 
	public Customer createCustomer(CustomerRequest customerRequest) {
		Customer customer = tallyCustomerDao.fetchCustomer(customerRequest.getUser_id());
		
		return null;
	}
		public List<Users> fetchTally(String first_name, String last_name) {
		return null;
	}

}
