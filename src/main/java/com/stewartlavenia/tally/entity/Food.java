package com.stewartlavenia.tally.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Food {
	private int food_pk;
	private String food_name;
	private int carb_serving_grams;
	private int calories_per_servings;
	private MealType meal_type;
	
	

}
