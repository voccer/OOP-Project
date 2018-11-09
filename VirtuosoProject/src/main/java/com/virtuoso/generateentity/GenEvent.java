package com.virtuoso.generateentity;

import com.virtuoso.entity.Event;

public class GenEvent extends GenEntity{
	public Event genEvent() {
		return new Event(getRandomLabel(), getRandomDescription(), getRandomLink(), getRandomDate());
	}
}
