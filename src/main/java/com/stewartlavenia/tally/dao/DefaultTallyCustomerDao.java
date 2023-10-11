package com.stewartlavenia.tally.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.stewartlavenia.tally.entity.Customer;
import com.stewartlavenia.tally.entity.Food;
import com.stewartlavenia.tally.entity.MealType;
import com.stewartlavenia.tally.entity.UserGoals;
import com.stewartlavenia.tally.entity.Users;

@Component
public class DefaultTallyCustomerDao implements TallyCustomerDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public Customer fetchCustomer(int userId) {
		String sql = " "
				+ "SELECT * "
				+ "FROM users "
				+ "WHERE user_id = 9";
		
		Map<String, Object> params = new HashMap<>();
		params.put("user_id", userId);
		
		return jdbcTemplate.query(sql, params, new CustomerResultSetExtractor());
				
	}

	/**
	 * 
	 * @author lavenia
	 *
	 */
	class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {
			@Override
			public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				//@formatter:off
				return Customer.builder()
						.userId(rs.getInt("user_id"))
						.firstName(rs.getString("first_name"))
						.lastName(rs.getString("last_name"))
						.email(rs.getString("email"))
						.phone(rs.getString("phone"))
						.build();
				//@formatter:on
			}
	}
	
	@Override
	public UserGoals fetchGoals(int goalsPk, int userFk, int calorieLimit, int dailyCalorieTotal, int carbsGramsLimit) {
		String sql = ""
				+ "SELECT * "
				+ "FROM user_goals "
				+ "WHERE goals_pk = :goals_pk "
				+ "And user_fk = :user_fk "
				+ "And calorie_limit "
				+ "And daily_calorie_total "
				+ "And carbs_grams_limit ";
		
		Map<String, Object> params = new HashMap<>();
		params.put("goals_pk", goalsPk);
		params.put("user_fk", userFk);
		params.put("calorie_limit", calorieLimit);
		params.put("daily_calorie_total", dailyCalorieTotal);
		params.put("carbs_grams_limit", carbsGramsLimit);
	
		return jdbcTemplate.query(sql, params, new UserGoalsResultSetExtractor() );
	
	}
	class UserGoalsResultSetExtractor implements ResultSetExtractor<UserGoals> {
		public UserGoals extractData(ResultSet rs) throws SQLException, DataAccessException {
			rs.next();
			//@formatter:off
			return UserGoals.builder()
					.goals_pk(rs.getLong(":goals_pk"))
					.user_fk(rs.getLong(":user_fk"))
					.calorie_limit(rs.getInt("calorie_limit"))
					.daily_calorie_total(rs.getInt("daily_calorie_total"))
					.carbs_grams_limit(rs.getInt("carbs_grams_limit"))
					.build();
			//@formatter:on
		}	

	} // ends UserGoalsResultSetExtractor class
	
	@Override
	public Food fetchFood(int foodPk, String foodName, int carbServingGrams, int calsPerServing, MealType mealType) {
		
		//@formatter: off
		String sql = " "
				+ "SELECT * "
				+ "FROM food "
				+ "WHERE food_pk = :food_pk "
				+ "AND food_name = food_name "
				+ "AND carb_serving_grams = carb_serving_grams "
				+ "AND calories_per_serving = calories_per_serving "
				+ "AND meal_type = meal_type ";
		//@formatter: on
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("food_pk", foodPk);
		params.put("food_name", foodName);
		params.put("carb_serving_grams", carbServingGrams);
		params.put("calories_per_serving", calsPerServing);
		params.put("meal_type", mealType);
		
		return jdbcTemplate.query(sql, params, new FoodResultSetExtractor());
		
	}
	
	class FoodResultSetExtractor implements ResultSetExtractor<Food> {
		public Food extractData(ResultSet rs) 
				throws SQLException, DataAccessException {
				rs.next();
				//@formatter:off
				return Food.builder()
						.food_pk(rs.getInt(":food_pk"))
						.food_name(rs.getString("food_name"))
						.carb_serving_grams(rs.getInt("carb_serving_grams"))
						.calories_per_servings(rs.getInt("calories_per_servings"))
						.meal_type(MealType.valueOf(rs.getString("meal_type")))
						.build();
				//@formatter:on
			}
		}
	
	// ------------------- when removed my super class goes red
		@Override
		public List<Users> fetchTally(String first_name, String last_name) {
			
			return null;
		}


	
} // end DefaultTallyCustomerDao class


	