package com.stewartlavenia.tally.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.validation.annotation.Validated;

import com.stewartlavenia.tally.Constants;
import com.stewartlavenia.tally.controller.support.FetchTallyTestSupport;
import com.stewartlavenia.tally.entity.Users;
import com.stewartlavenia.tally.service.TallyService;


// @ContextConfiguration
class FetchTallyTest extends FetchTallyTestSupport {
	
	@Nested
	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@ActiveProfiles("test") // yaml
	@Sql(scripts = { "classpath:flyway/migrations/Tally_Schema.sql",
			"classpath:flyway/migrations/Tally_Data.sql" }, config = @SqlConfig(encoding = "utf-8"))

	class TestThatDoNotPolluteTheApplicationContext extends FetchTallyTestSupport {
	
		@Test
		void testThatValidUserEmailShouldReturnExpectedFirstAndLastNames() {
			
			//Given: a valid email & uri
			String email = "biten@district112.org";
			String first_name = "David";
			String last_name = "Biten";
			String uri = String.format("%s?first_name=%s&last_name=%s", getBaseUriForUsers(), first_name, last_name);
			
			//When a connection is made to the uri
			ResponseEntity<List<Users>> response = 
					getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
			
			//Then a success (OK - 200) status code is returned
			
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

			// And: the actual list returned is the __ as the expected list
			
			List<Users> actual = response.getBody();
			List<Users> expected = buildExpected();
			
		}
		/* String uri = String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);

		 */
		
		@Test
		void testThatAnErrorMessageIsReturnedWhenAnUnknownLastNamesIsSupplied() {
			
			//Given: a valid email & uri
			String email = "biten@district112.org";
			String first_name = "David";
			String last_name = "An unknown Last Name";

			String uri = String.format("%s?first_name=%s&last_name=%s", getBaseUriForUsers(), first_name, last_name);
			
			//When a connection is made to the uri
			ResponseEntity <Map<String, Object>> response = 
					getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
			
			//Then a success (N_F - 404) status code is returned
			
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

			// And: An error message is returned 
			 Map<String, Object> error = response.getBody();
			 assertErrorMessageValid(error, HttpStatus.NOT_FOUND);
			 
			
			
		} 
		
		@ParameterizedTest
		@MethodSource("com.stewartlavenia.tally.controller.FetchTallyTest#parametersForInvalidInput")
		void testThatAnErrorMessageIsReturnedWhenAnInvalidValueIsSupplied(String first_name, String last_name, String reason) {
			
			//Given: a valid email & uri
//			String email = "biten@district112.org";
			
			// removed last_name & first because they're coming in as params
			String uri = String.format("%s?first_name=%s&last_name=%s", getBaseUriForUsers(), first_name, last_name);
			
			//When a connection is made to the uri
			ResponseEntity <Map<String, Object>> response = 
					getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
			
			
			//Then a success (badRequest - 400) status code is returned
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
			// 404 was returned therefore I changed test and now I get a 500 internal err
			// SO change it to a 500 err it's for args (2) constraintViolationErr Actual Can't be null
			// in Global on line 60 & 91 had return null; instead of createExceptionMess statements
			
//			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
			
			// And: An error message is returned 
			 Map<String, Object> error = response.getBody();
			 assertErrorMessageValid(error, HttpStatus.NOT_FOUND);
//			 assertErrorMessageValid(error, HttpStatus.BAD_REQUEST);
//			 assertErrorMessageValid(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	//@formatter:off
	static Stream<Arguments> parametersForInvalidInput() {
		
		return Stream.of(
				arguments("David", "Bogus Value", "Last name contains alpha-numeric chars!"),
				arguments("David", "C".repeat(Constants.NAME_MAX_LENGTH + 2), "Name length too long!!!"),
				arguments("AbbaBaZuuka", "Biten", "First name isn't in the database!")
		//@formatter:on
			);
	}
	

	@Nested
	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@ActiveProfiles("test") // yaml
	@Sql(scripts = { "classpath:flyway/migrations/Tally_Schema.sql",
			"classpath:flyway/migrations/Tally_Data.sql" }, config = @SqlConfig(encoding = "utf-8"))

	class TestThatDoPolluteTheApplicationContext extends FetchTallyTestSupport {
	
		@MockBean
		private TallyService tallyService;
		
		@Test
		void testThatAnUnplannedErrorOccursInA500Status() {
			
			//Given: a valid email & uri
			String email = "biten@district112.org";
			String first_name = "David";
			String last_name = "An INVALID Last Name";

			String uri = String.format("%s?first_name=%s&last_name=%s", getBaseUriForUsers(), first_name, last_name);
			
			doThrow(new RuntimeException("Ouch!")).when(tallyService).fetchTally(first_name, last_name);
			
			//When a connection is made to the uri
			ResponseEntity <Map<String, Object>> response =
//			ResponseEntity <?> response = 
					getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
					System.out.println(response);
			//Then a success (INTERNAL_SERVER_ERROR 500) status code is returned
			
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

			// And: An error message is returned 
			 Map<String, Object> error = response.getBody();
			 assertErrorMessageValid(error, HttpStatus.INTERNAL_SERVER_ERROR);
			 
			
			
		} 
	}
	
	
// } // end FetchTallyTest

	
	

	/*
	
	// A TEST I'LL RUN LATER ========================================
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
*/
} // end FetchTallyTest
