package com.virtuoso.generateentity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.virtuoso.entity.Person;

public class GenPerson extends GenEntity{
	private List<String> statusList =  new ArrayList<>();
	private List<String> pplLabelList = new ArrayList<>();
	private List<String> pplDescList = new ArrayList<>();
	
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
	
	@Override
	public void setLabelList(String fileName) {
		// TODO Auto-generated method stub
		setList(fileName, pplLabelList);	
	}

	@Override
	public void setDescriptionList(String fileName) {
		// TODO Auto-generated method stub
		setList(fileName, pplDescList);
	}

	@Override
	public String getRandomLabel() {
		// TODO Auto-generated method stub
		return pplLabelList.get((int)(Math.random() * pplLabelList.size() + 0));
	}

	@Override
	public String getRandomDescription() {
		// TODO Auto-generated method stub
		return pplDescList.get((int)(Math.random() * pplDescList.size() + 0));
	}

	public Person genPerson() {
		return new Person(this.getRandomLabel(), this.getRandomDescription(), getRandomLink(), getRandomDate(), getRandomStatus());
	}
}
