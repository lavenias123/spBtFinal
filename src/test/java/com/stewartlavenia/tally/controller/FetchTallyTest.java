package com.stewartlavenia.tally.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.stewartlavenia.tally.controller.support.FetchTallyTestSupport;
import com.stewartlavenia.tally.entity.Users;

@Nested
@ContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // yaml
@Sql(scripts = { "classpath:flyway/migrations/Tally_Schema.sql",
		"classpath:flyway/migrations/Tally_Data.sql" }, config = @SqlConfig(encoding = "utf-8"))

class FetchTallyTest extends FetchTallyTestSupport {
	
	
	@Test
	void testThatValidUserEmailShouldReturnExpectedFirstAndLastNames() {
		
		//Given: a valid email & uri
		String email = "biten@district112.org";
		String first_name = "David";
		String last_name = "Biten";
		String uri = String.format("%s?first_name=%s&last_name=%s", getBaseUri(), first_name, last_name);
		
		//When a connection is made to the uri
		ResponseEntity<List<Users>> response = 
				getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
		
		//Then a success (OK - 200) status code is returned
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		// And: the actual list returned is the __ as the expected list
		
		List<Users> actual = response.getBody();
		List<Users> expected = buildExpected();
		
	}
	
	@Test
	void testThatAnErrorMessageIsReturnedWhenAnInvalidLastNamesIsSupplied() {
		
		//Given: a valid email & uri
		String email = "biten@district112.org";
		String first_name = "David";
		String last_name = "Invalid Last Name";

		String uri = String.format("%s?first_name=%s&last_name=%s", getBaseUri(), first_name, last_name);
		
		//When a connection is made to the uri
		ResponseEntity <Map<String, Object>> response = 
				getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
		
		//Then a success (OK - 200) status code is returned
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

		// And: An error message is returned 
		 Map<String, Object> error = response.getBody();
		 //@formatter:off
		 assertThat(error)
		 .containsKey("message")
		 .containsEntry("Status code", HttpStatus.NOT_FOUND.value())
		 .containsEntry("uri", "/tallyCalCarbsDaily")
		 .containsKey("timestamp")
		 .containsEntry("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
		 
		 //@formatter:on
	}	
	
//	@Test
//	void testThatValidUserEmailShouldReturnExpectedFirstAndLastNames() {
//		
//		//Given: a valid email & uri
//		String email = "biten@district112.org";
//		String first_name = "David";
//		String last_name = "Biten";
//
//		String uri = String.format("%s?first_name=%s&last_name=%s", getBaseUri(), first_name, last_name);
//		
//		//When a connection is made to the uri
//		ResponseEntity<List<Users>> response = 
////				getRestTemplate().getForEntity(uri, Users.class); typo getRestTemplate? err when get is removed fetchJeepTest 110
////				getRestTemplate().getForEntity(uri, Users.class);
//				getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
//		
//		//Then a success (OK - 200) status code is returned
//		
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//		// And: the actual list returned is the __ as the expected list
//		
//		List<Users> actual = response.getBody();
//		List<Users> expected = buildExpected();
//		
//	}

}
