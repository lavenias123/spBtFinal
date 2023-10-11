package com.stewartlavenia.tally.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
	
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private int calorieLimit;
	private int dailyCalorieTotal;
	private int carbsGramsLimit;
}
