package com.virtuoso.generateentity;

import com.virtuoso.entity.Time;

public class GenTime extends GenEntity{
	public Time genTime() {
		return new Time(getRandomLabel(), getRandomDescription(), getRandomLink(), getRandomDate());
	}
}
