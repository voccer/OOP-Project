package com.virtuoso.relationship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenRelationship {
	private List<String> relDescriptionList = new ArrayList<String>();
	
	public void setRelDescriptionList(String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(fileName));
		while (scanner.hasNextLine()) {
			relDescriptionList.add(scanner.nextLine());
		}
		scanner.close();
	}
	public String genRandomRelationshipDescription() {
		return relDescriptionList.get((int)(Math.random() * relDescriptionList.size() + 0));
	}
	
	public Relationship genRandomRelationship() {
		return new Relationship(relDescriptionList.get((int)(Math.random() * relDescriptionList.size() + 0)));
	}
}
