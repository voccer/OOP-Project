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
	private static Random RANDOM = new Random();
	
	private static int noEntity = 0;
	private static int noRelationship = 0;
	
	private static int noLink = 1000;
	private static int noDate = 1000;

	
	private static FileName fileName = new FileName();
	
	private static String personLabelFileName = fileName.PERSON_LABEL;
	private static String personDescriptionFileName = fileName.PERSON_DESCRIPTION;
	private static String personStatusFileName = fileName.PERSON_STATUS;
	
	private static String organizationLabelFileName = fileName.ORGANIZATION_LABEL;
	private static String organizationDescriptionFileName = fileName.ORGANIZATION_DESCRIPTION;
	private static String organizationHeadquarterFileName = fileName.ORGANIZATION_HEADQUARTER;
	
	private static String countryLabelFileName = fileName.COUNTRY_LABEL;
	private static String countryDescriptionFileName = fileName.COUNTRY_DESCRIPTION;
	private static String countryContinentFileName = fileName.COUNTRY_CONTINENT;
	
	private static String locationLabelFileName = fileName.LOCATION_LABEL;
	private static String locationDescriptionFileName = fileName.LOCATION_DESCRIPTION;
	
	private static String timeLabelFileName = fileName.TIME_LABEL;
	private static String timeDescriptionFileName = fileName.TIME_DESCRIPTION;
	
	private static String eventLabelFileName = fileName.EVENT_LABEL;
	private static String eventDescriptionFileName = fileName.EVENT_DESCRIPTION;
	
	
	private DatabaseConnection databaseConnect;
	private GenRandomEntity genRandomEntity;
	private GenRelationship genRelationship;
	
	private List<IRI> entityIRIList;
	
	public DatabaseGeneration() throws FileNotFoundException {
		databaseConnect = new DatabaseConnection();
		genRandomEntity = new GenRandomEntity();
		genRelationship = new GenRelationship();
		
		genRandomEntity.setEntities(noLink, noDate);
		genRandomEntity.setPeople(personLabelFileName, personDescriptionFileName, personStatusFileName);
		genRandomEntity.setOrganizations(organizationLabelFileName, organizationDescriptionFileName, organizationHeadquarterFileName);
		genRandomEntity.setCountries(countryLabelFileName, countryDescriptionFileName, countryContinentFileName);
		genRandomEntity.setLocations(locationLabelFileName, locationDescriptionFileName);
		genRandomEntity.setTime(timeLabelFileName, timeDescriptionFileName);
		genRandomEntity.setEvents(eventLabelFileName, eventDescriptionFileName);
		
		genRelationship.setRelDescriptionList(fileName.RELATIONSHIP_DESCRIPTION);
		
		entityIRIList = new ArrayList<>();
	}
	
	private void generateEntity(int numberOfEntity) {
		if(numberOfEntity > noEntity) {
			Entity entity = null;
			for(int i = 0; i < numberOfEntity - noEntity; i++) {
				entity = genRandomEntity.genRandomEntity();
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
