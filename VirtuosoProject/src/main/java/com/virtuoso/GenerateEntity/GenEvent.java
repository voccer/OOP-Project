package com.virtuoso.GenerateEntity;

import com.virtuoso.Entity.Event;

public class GenEvent extends GenEntity{
	public Event genEvent() {
		return new Event(getRandomLabel(), getRandomDescription(), getRandomLink(), getRandomDate());
	}
}
