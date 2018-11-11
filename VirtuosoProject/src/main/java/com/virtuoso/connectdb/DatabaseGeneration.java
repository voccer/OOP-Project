package com.virtuoso.connectdb;



import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.rdf4j.model.IRI;

import com.virtuoso.entity.Entity;
import com.virtuoso.generateentity.GenRandomEntity;
import com.virtuoso.relationship.GenRelationship;

public class DatabaseGeneration {
	private static final Random RANDOM = new Random();
	
	private static int noEntity = 0;
	private static int noRelationship = 0;
	
	private static int noLink = 1000;
	private static int noDate = 1000;

	
	private static FileName fileName = new FileName();
	
	private static String personLabelFileName = fileName.PERSONLABEL;
	private static String personDescriptionFileName = fileName.PERSONDESCRIPTION;
	private static String personStatusFileName = fileName.PERSONSTATUS;
	
	private static String organizationLabelFileName = fileName.ORGANIZATIONLABEL;
	private static String organizationDescriptionFileName = fileName.ORGANIZATIONDESCRIPTION;
	private static String headquarterFileName = fileName.HEADQUARTER;
	
	private static String countryLabelFileName = fileName.COUNTRYLABEL;
	private static String countryDescriptionFileName = fileName.COUNTRYDESCRIPTION;
	private static String countryContinentFileName = fileName.COUNTRYCONTINENT;
	
	private static String locationLabelFileName = fileName.LOCATIONLABEL;
	private static String locationDescriptionFileName = fileName.LOCATIONDESCRIPTION;
	
	private static String timeLabelFileName = fileName.TIMELABEL;
	private static String timeDescriptionFileName = fileName.TIMEDESCRIPTION;
	
	private static String eventLabelFileName = fileName.EVENTLABEL;
	private static String eventDescriptionFileName = fileName.EVENTDESCRIPTION;
	
	private DatabaseConnection databaseConnect;
	private GenRandomEntity genRandomEntity;
	private GenRelationship genRelationship;
	
	private List<IRI> entityIRIList;
	
	public DatabaseGeneration() throws FileNotFoundException {
		databaseConnect = new DatabaseConnection();
		genRandomEntity = new GenRandomEntity();
		genRelationship = new GenRelationship();
		
		genRandomEntity.setEntityGeneration(noLink, noDate);
		genRandomEntity.setPersonGeneration(personLabelFileName, personDescriptionFileName, personStatusFileName);
		genRandomEntity.setOrganizationGeneration(organizationLabelFileName, organizationDescriptionFileName, headquarterFileName);
		genRandomEntity.setCountryGeneration(countryLabelFileName, countryDescriptionFileName, countryContinentFileName);
		genRandomEntity.setLocationGeneration(locationLabelFileName, locationDescriptionFileName);
		genRandomEntity.setTimeGeneration(timeLabelFileName, timeDescriptionFileName);
		genRandomEntity.setEventGeneration(eventLabelFileName, eventDescriptionFileName);
		
		genRelationship.setRelDescriptionList(fileName.RELATIONSHIPDESCRIPTION);
		
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
	
	public DatabaseConnection getDatabaseConnect() {
		return databaseConnect;
	}
	
	public void clearStatements() {
		databaseConnect.clear();
	}
}
