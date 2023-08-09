package com.stewartlavenia.tally.entity;

import lombok.Builder;
import lombok.Data;
//import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstrutor

public class Users {
	private Long user_pk;
	private String first_name;
	private String last_name;
	private String email;
}
