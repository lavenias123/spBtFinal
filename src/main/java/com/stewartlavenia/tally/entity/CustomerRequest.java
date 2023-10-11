package com.stewartlavenia.tally.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Email;


import lombok.Data;

@Data
public class CustomerRequest {
	
	@NotNull
	private int user_id;
	
	@NotNull
	@Length(max = 30)
	@Pattern(regexp = "[\\w\\s]*")
	private String first_name;
	
	@NotNull
	@Length(max = 30)
	@Pattern(regexp = "[\\w\\s]*")
	private String last_name;
	
	@SuppressWarnings("deprecation")
	@NotNull
	@Length(max = 30)
//	@Pattern(regexp = "[\\w\\s]*")
	@Email
	private String email;
	
	@NotNull
	@Pattern(regexp = "[0-9*#+() -]*")
	@Size(min=10, max=15)
	private String phone;
	
	
	@NotNull
	@Min(1200)
	@Max(2000)
	private int calorie_limit;
	
	@NotNull
	@Min(1600)
	@Max(3000)
	private int daily_calorie_total;
	
	@NotNull
	@Min(400)
	@Max(1000)
	private int carbs_grams_limit;
}
