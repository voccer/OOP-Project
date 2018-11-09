package com.virtuoso.generateentity;

import com.virtuoso.entity.Location;

public class GenLocation extends GenEntity{
	public Location genLocation() {
		return new Location(getRandomLabel(), getRandomDescription(), getRandomLink(), getRandomDate());
	}
}
