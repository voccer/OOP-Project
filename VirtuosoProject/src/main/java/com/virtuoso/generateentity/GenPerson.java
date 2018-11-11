package com.virtuoso.generateentity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.virtuoso.entity.Person;

public class GenPerson extends GenEntity{
	private List<String> statusList =  new ArrayList<String>();
	
	public void setStatusList (String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(fileName));
		while (scanner.hasNextLine()) {
			statusList.add(scanner.nextLine());
		}
		scanner.close();
	}
	public String getRandomStatus() {
		return statusList.get((int)(Math.random() * statusList.size() + 0));
	}
	
	public Person genPerson() {
		return new Person(getRandomLabel(), getRandomDescription(), getRandomLink(), getRandomDate(), getRandomStatus());
	}
}
