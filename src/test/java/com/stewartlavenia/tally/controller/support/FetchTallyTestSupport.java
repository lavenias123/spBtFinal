package com.stewartlavenia.tally.controller.support;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.stewartlavenia.tally.entity.Users;

public class FetchTallyTestSupport extends BaseTallyTest {

	protected List<Users> buildExpected() {
		List<Users> list = new LinkedList<>();
		
		//@formatter:off
		list.add(Users.builder()
				.first_name("Dan")
				.last_name("BoshDan")
				.email("info@BoshDan.com")
				.phone("213-666-4444")
				.build());
		
		list.add(Users.builder()
				.first_name("Carol")
				.last_name("Butters")
				.email("sam@d1tm.org")
				.phone("767-888-2323")
				.build());
		//@formatter:on
		Collections.sort(list);
		
		return list;
	}
	
}
