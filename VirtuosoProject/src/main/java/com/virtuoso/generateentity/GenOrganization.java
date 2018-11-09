package com.virtuoso.generateentity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.virtuoso.entity.Organization;

public class GenOrganization extends GenEntity{
	private List<String> headquarterList = new ArrayList<String>();
	
	public void setHeadquarterList(String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(fileName));
		while (scanner.hasNextLine()) {
			headquarterList.add(scanner.nextLine());
		}
		scanner.close();
	}
	
	public String getRandomHeadquarter() {
		return headquarterList.get((int)(Math.random() * headquarterList.size() + 0));
	}
	
	public Organization genOrganization() {
		return new Organization(getRandomLabel(), getRandomDescription(), getRandomLink(), getRandomDate(), getRandomHeadquarter());
	}
}
