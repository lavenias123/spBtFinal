package com.stewartlavenia.tally.controller;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.stewartlavenia.tally.entity.Users;

import com.stewartlavenia.tally.controller.support.FetchTallyTestSupport;

@Nested
@ContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = { "classpath:flyway/migrations/Tally_Schema.sql",
		"classpath:flyway/migrations/Tally_Data.sql" }, config = @SqlConfig(encoding = "utf-8"))

class FetchTallyTest extends FetchTallyTestSupport {

	@Test
	void testThatValidUserEmailShouldReturnExpectedFirstAndLastNames() {
		
		String email = "otterR@district17.org";
		String first_name = "Lavenia";
		String last_name = "Stewart";
		String uri = String.format("%s?first_name=%s&last_name=%s", getBaseUri(), first_name, last_name);
	
//	void testThatFirstAndLastNamesAreReturnedWhenValidEmailIsSupplied() {
//		
//		//Given: a valid email & uri
//		String email = "otterR@district17.org";
//		String uri = String.format("%s?email=%sfirst_name=%s&last_name=%s", getBaseUri(), first_name, last_name);
		
		System.out.println(uri);
	}

}
