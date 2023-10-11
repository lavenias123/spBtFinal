package com.stewartlavenia.tally.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstrutor
public class UserGoals {

	private Long goals_pk;
	private Long user_fk;
	private int calorie_limit;
	private int daily_calorie_total;
	private int carbs_grams_limit;
	
	@JsonIgnore
	public Long getUserPK() {
		return goals_pk;
		
	}

}
