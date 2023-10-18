package com.stewartlavenia.tally.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserFood {
	private int userFk;
	private int foodFk;
}
