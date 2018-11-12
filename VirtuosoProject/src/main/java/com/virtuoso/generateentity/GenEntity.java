package com.virtuoso.generateentity;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GenEntity {
	private static List<String> linkList = new ArrayList<String>();
	private static List<String> dateList = new ArrayList<String>();

	private String prefix = "http://www.example.org/link#";
	
	public void setLinkList(int numberOfLinks) {
		for (int i = 0; i < numberOfLinks; i++) {
			linkList.add(prefix + i);
		}
	}
	
	public List<String> getLinkList() {
		return linkList;
	}
	
	public String generateRandomDate() {
		LocalDate startDate = LocalDate.of(1900, 1, 1); //start date
		long start = startDate.toEpochDay();

		LocalDate endDate = LocalDate.now(); //end date
		long end = endDate.toEpochDay();

		long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
		return LocalDate.ofEpochDay(randomEpochDay).toString();
	}
	
	public void setDateList(int numberOfDates) {
		for (int i = 0; i < numberOfDates; i++) {
			String date = generateRandomDate();
			dateList.add(date);
		}
	}
	
	public List<String> getDateList() {
		return dateList;
	}
	
	public String getRandomLink() {
		return linkList.get((int )(Math.random() * linkList.size() + 0));
	}
	
	public String getRandomDate() {
		return dateList.get((int)(Math.random() * dateList.size() + 0));
	}
	
	public void setList(String fileName, List<String> entityList) {
		Scanner scanner;
		try {
			scanner = new Scanner(new File(fileName));
			while (scanner.hasNextLine()) {
				entityList.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setLabelList(String fileName) {

	}
	
	public void setDescriptionList(String fileName) {
		
	}
	
	public String getRandomLabel() {
		return prefix;
	}

	public String getRandomDescription() {
		return prefix;
	}
}
