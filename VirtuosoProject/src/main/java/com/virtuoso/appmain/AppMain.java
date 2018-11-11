package com.virtuoso.appmain;


import java.io.FileNotFoundException;

import org.eclipse.rdf4j.model.vocabulary.RDF;

import com.virtuoso.connectdb.DatabaseGeneration;

public class AppMain {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Begin");
		
		int[] n = {100, 5000, 60000, 1000000, 15000000};
		int[] m = {200, 7000, 80000, 2000000, 17000000};
		long startTime = System.currentTimeMillis();
		DatabaseGeneration databaseGeneration = new DatabaseGeneration();
		databaseGeneration.clearStatements();
		long endTime = System.currentTimeMillis();
		System.out.println("Time to init DatabaseGeneration: " + (endTime - startTime));
		
		for(int i = 0; i < 5; i++) {
			databaseGeneration.generateDatabase(n[i], m[i]);
			long time = databaseGeneration.getDatabaseConnect().queryStatementTime(null, RDF.TYPE, databaseGeneration.getDatabaseConnect().getPersonType(), null);
			System.out.println("Time to query in " + n[i] + " entities and " + m[i] + " relationship is " + time);
		}
		System.out.println("End");
	}
}
