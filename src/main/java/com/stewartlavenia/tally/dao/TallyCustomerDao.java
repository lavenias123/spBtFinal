package com.stewartlavenia.tally.dao;

import com.stewartlavenia.tally.entity.Customer;
import com.stewartlavenia.tally.entity.CustomerRequest;
import com.stewartlavenia.tally.entity.Food;
import com.stewartlavenia.tally.entity.MealType;
import com.stewartlavenia.tally.entity.UserGoals;

// Dr. R doesn't have extends... Mike and I tried changing to implements compiler didn't like that
public interface TallyCustomerDao extends TallyDao {
	
	Customer fetchCustomer(int userId);

	UserGoals fetchGoals(int goalsPk, int userFk, int calorieLimit, int dailyCalorieTotal, int carbsGramsLimit);

	Food fetchFood(int foodPk, String foodName, int carbServingGrams, int calsPerServing, MealType mealType);


}
