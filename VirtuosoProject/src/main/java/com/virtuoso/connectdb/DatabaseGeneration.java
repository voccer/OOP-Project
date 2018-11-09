package com.virtuoso.connectdb;



import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.rdf4j.model.IRI;

import com.virtuoso.constants.FileName;
import com.virtuoso.entity.Entity;
import com.virtuoso.generateentity.GenRandomEntity;
import com.virtuoso.relationship.GenRelationship;

public class DatabaseGeneration {
	private static final Random RANDOM = new Random();
	
	private static int noEntity = 0;
	private static int noRelationship = 0;
	
	private static int noLink = 1000;
	private static int noDate = 1000;
	private static String startDate = "2011-01-01";
	
	private static FileName fileName = new FileName();
	private static String personLabelFileName = fileName.getCountryLabel();
	private static String personDescriptionFileName = fileName.getPersonDescription();;
	
	private static String organizationLabelFileName = fileName.getOrganizationLabel();
	private static String organizationDescriptionFileName = fileName.getOrganizationDesciption();
	private static String headquarterFileName = fileName.getHeadquarter();
	
	private static String countryLabelFileName = fileName.getCountryLabel();
	private static String countryDescriptionFileName = fileName.getCountryDescription();
	
	private static String locationLabelFileName = fileName.getLocationLabel();
	private static String locationDescriptionFileName = fileName.getLocationDescription();
	
	private static String timeLabelFileName = fileName.getTimeLabel();
	private static String timeDescriptionFileName = fileName.getTimeDescription();
	
	private static String eventLabelFileName = fileName.getEventLabel();
	private static String eventDescriptionFileName = fileName.getEventDescription();
	
	private DatabaseConnect databaseConnect;
	private GenRandomEntity genRandomEntity;
	private GenRelationship genRelationship;
	
	private List<IRI> entityIRIList;
	
	public DatabaseGeneration() throws FileNotFoundException {
		databaseConnect = new DatabaseConnect();
		genRandomEntity = new GenRandomEntity();
		genRelationship = new GenRelationship();
		
		
		genRandomEntity.setEntityGeneration(noLink, noDate, startDate);
		genRandomEntity.setPersonGeneration(personLabelFileName, personDescriptionFileName);
		genRandomEntity.setOrganizationGeneration(organizationLabelFileName, organizationDescriptionFileName, headquarterFileName);
		genRandomEntity.setCountryGeneration(countryLabelFileName, countryDescriptionFileName);
		genRandomEntity.setLocationGeneration(locationLabelFileName, locationDescriptionFileName);
		genRandomEntity.setTimeGeneration(timeLabelFileName, timeDescriptionFileName);
		genRandomEntity.setEventGeneration(eventLabelFileName, eventDescriptionFileName);
		
		genRelationship.setRelDescriptionList(fileName.getRelationshipDescription());
		
		entityIRIList = new ArrayList<>();
	}
	
	private void generateEntity(int numberOfEntity) {
		if(numberOfEntity > noEntity) {
			Entity entity = null;
			for(int i = 0; i < numberOfEntity - noEntity; i++) {
				entity = genRandomEntity.generateRandomEntity();
				entityIRIList.add(databaseConnect.insertEntity(entity));
			}
		}
		noEntity = numberOfEntity;
	}
	
	private void generateRelationship(int numberOfRelationship) {
		if(numberOfRelationship > noRelationship) {
			IRI entity1 = null;
			IRI entity2 = null;
			IRI relationship = null;
			
			for(int i = 0; i < numberOfRelationship - noRelationship; i++) {
				entity1 = entityIRIList.get(RANDOM.nextInt(noEntity));
				entity2 = entityIRIList.get(RANDOM.nextInt(noEntity));
				relationship = databaseConnect.createRelationship(genRelationship.genRandomRelationshipDescription());
				databaseConnect.insertStatement(entity1, relationship, entity2);
			}
		}
		noRelationship = numberOfRelationship;
	}
	
	public void generateDatabase(int numberOfEntity, int numberOfRelationship) {
		generateEntity(numberOfEntity);
		generateRelationship(numberOfRelationship);
	}
	
	public DatabaseConnect getDatabaseConnect() {
		return databaseConnect;
	}
	
	public void clearStatements() {
		databaseConnect.clear();
	}
}
