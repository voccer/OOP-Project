package com.virtuoso.generateentity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.virtuoso.entity.Country;

public class GenCountry extends GenEntity{
	private List<String> continentList = new ArrayList<String>();
	
	public void setContinentList(String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(fileName));
		while (scanner.hasNextLine()) {
			continentList.add(scanner.nextLine());
		}
		scanner.close();
	}
	
	public String getRandomContinent() {
		return continentList.get((int)(Math.random() * continentList.size() + 0));
	}
	
	public Country genCountry() {
		return new Country(getRandomLabel(), getRandomDescription(), getRandomLink(), getRandomDate());
	}
}
