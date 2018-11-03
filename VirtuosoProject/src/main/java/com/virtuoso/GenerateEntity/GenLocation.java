package com.virtuoso.GenerateEntity;

import com.virtuoso.Entity.Location;

public class GenLocation extends GenEntity{
	public Location genLocation() {
		return new Location(getRandomLabel(), getRandomDescription(), getRandomLink(), getRandomDate());
	}
}
