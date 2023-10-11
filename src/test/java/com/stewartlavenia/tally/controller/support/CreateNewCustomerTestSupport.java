package com.stewartlavenia.tally.controller.support;

public class CreateNewCustomerTestSupport extends BaseTallyTest{
	/**
	 * 
	 * @return
	 */
	protected String createNewCustomerBody() {
		//@formatter:off
		 
		return "{\n"
			+ "	\"first_name\":\"Jennifer\",\n"
			+ "	\"last_name\":\"Daniels\",\n"
			+ "  \"email\":\"hr@JenDaniels.com\",\n"
			+ "  \"phone\":\"709-444-6228\",\n"
			+ "  \"calorie_limit\":\"1800\",\n"
			+ "  \"daily_calorie_total\":\"1825\",\n"
			+ "  \"carbs_grams_limit\":\"435\"\n"
			+ "}";

		//@formatter:on
	}
}
