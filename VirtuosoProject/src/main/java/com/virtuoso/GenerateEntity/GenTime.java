package com.virtuoso.GenerateEntity;

import com.virtuoso.Entity.Time;

public class GenTime extends GenEntity{
	public Time genTime() {
		return new Time(getRandomLabel(), getRandomDescription(), getRandomLink(), getRandomDate());
	}
}
