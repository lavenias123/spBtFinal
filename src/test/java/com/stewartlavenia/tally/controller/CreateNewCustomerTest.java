package com.stewartlavenia.tally.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.stewartlavenia.tally.controller.support.CreateNewCustomerTestSupport;
import com.stewartlavenia.tally.entity.Customer;
import com.stewartlavenia.tally.entity.CustomerRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // yaml
@Sql(scripts = { "classpath:flyway/migrations/Tally_Schema.sql",
		"classpath:flyway/migrations/Tally_Data.sql" }, config = @SqlConfig(encoding = "utf-8"))
class CreateNewCustomerTest extends CreateNewCustomerTestSupport{

/**
 * 
 */
	@Test
	void testCreateNewCustomerReturnsSuccess201() {
		
		// Given: a new customer account as JSON
		String body = createNewCustomerBody();
		String uri = getBaseUriForCustomers();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
		
		// When: the new account is sent
//		ResponseEntity<?> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity, Object.class);
		
		ResponseEntity<CustomerRequest> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity, CustomerRequest.class);
	
		
		// Then: a 201 status is returned
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		// And: the returned account is correct
		assertThat(response.getBody()).isNotNull();
		
		CustomerRequest customer = response.getBody();
		assertThat(customer.getFirst_name()).isEqualTo("Jennifer");
		assertThat(customer.getFirst_name()).isEqualTo("Daniels");
		assertThat(customer.getEmail()).isEqualTo("email");
		assertThat(customer.getPhone()).isEqualTo("709-444-6228");
		assertThat(customer.getCalorie_limit()).isEqualTo(1800);
		assertThat(customer.getDaily_calorie_total()).isEqualTo(1825);
		assertThat(customer.getCarbs_grams_limit()).isEqualTo(435);
		
		
	}



}
