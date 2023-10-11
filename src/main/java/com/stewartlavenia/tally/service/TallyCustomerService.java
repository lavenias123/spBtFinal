package com.stewartlavenia.tally.service;

import com.stewartlavenia.tally.entity.Customer;
import com.stewartlavenia.tally.entity.CustomerRequest;

public interface TallyCustomerService extends TallyService {

	Customer createCustomer(CustomerRequest customerRequest);

}
