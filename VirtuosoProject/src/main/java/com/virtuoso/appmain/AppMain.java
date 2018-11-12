package com.virtuoso.appmain;


import java.io.FileNotFoundException;

import org.eclipse.rdf4j.model.vocabulary.RDF;

import com.virtuoso.connectdb.DBGeneration;

public class AppMain {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Begin");
		
		int[] n = {100, 5000, 60000, 1000000, 15000000};
		int[] m = {200, 7000, 80000, 2000000, 17000000};
		long startTime = System.currentTimeMillis();
		DBGeneration genDB = new DBGeneration();
		genDB.clearStatements();
		long endTime = System.currentTimeMillis();
		
		System.out.println("Time to init DatabaseGeneration: " + (endTime - startTime));
		
		System.exit(1);
		for(int i = 0; i < 5; i++) {
			genDB.genDB(n[i], m[i]);
			long time = genDB.getDatabaseConnect().queryStatementTime(null, RDF.TYPE, genDB.getDatabaseConnect().getPERSON(), null);
			System.out.println("Time to query for " + n[i] + " entities and " + m[i] + " relationship is " + time);
		}
		System.out.println("End");
	}
}
