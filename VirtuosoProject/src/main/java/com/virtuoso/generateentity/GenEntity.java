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
	private static List<String> labelList = new ArrayList<String>();
	private static List<String> descriptionList = new ArrayList<String>();
	private String prefix = "http://www.example.com/link#";
	
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
	
	public void setLabelList(String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(fileName));
		while (scanner.hasNextLine()) {
			labelList.add(scanner.nextLine());
		}
		scanner.close();
	}
	
	public void setDescriptionList(String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(fileName));
		while (scanner.hasNextLine()) {
			descriptionList.add(scanner.nextLine());
			
		}
		scanner.close();
	}
	
	public String getRandomLabel() {
		return labelList.get((int)(Math.random() * labelList.size() + 0));
	}

	public String getRandomDescription() {
		return descriptionList.get((int)(Math.random() * descriptionList.size() + 0));
		
	}
}
