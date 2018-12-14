package com.virtuoso.connectdb;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.rdf4j.model.IRI;

import com.virtuoso.entity.Country;
import com.virtuoso.entity.Entity;
import com.virtuoso.entity.Event;
import com.virtuoso.entity.Location;
import com.virtuoso.entity.Organization;
import com.virtuoso.entity.Person;
import com.virtuoso.entity.Time;
import com.virtuoso.generateentity.GenRandomEntity;
import com.virtuoso.relationship.GenRelationship;

public class DBGeneration {
	private static int numberOfEntities = 0;
	private static int numberOfRelationships = 0;
	
	private static int numberOfLinks = 10000;
	private static int numberOfDates = 10000;

	private static ConstantsFileName fileName = new ConstantsFileName();	
	
	private EntityIRI entityIRI = new EntityIRI();
	private GenRandomEntity genRandomEntity = new GenRandomEntity();
	private GenRelationship genRelationship = new GenRelationship();
//	private GenRelationship genRelationshipPerPer = new GenRelationship();
	private CreateEntitiesIRI entitiesIRI = new CreateEntitiesIRI();
	
	
	private List<IRI> personIRIList;
	private List<IRI> locIRIList;
	private List<IRI> orgIRIList;
	private List<IRI> eventIRIList;
	private List<IRI> timeIRIList;
	private List<IRI> countryIRIList;
//	private List<IRI> entitiesIRIList;
	
	public DBGeneration() throws FileNotFoundException {
		
		genRandomEntity.setEntities(numberOfLinks, numberOfDates);
		genRandomEntity.setPeople(fileName.PERSON_LABEL, fileName.PERSON_DESCRIPTION, fileName.PERSON_STATUS);
		genRandomEntity.setOrganizations(fileName.ORGANIZATION_LABEL, fileName.ORGANIZATION_DESCRIPTION, fileName.ORGANIZATION_HEADQUARTER);
		genRandomEntity.setCountries(fileName.COUNTRY_LABEL, fileName.COUNTRY_DESCRIPTION, fileName.COUNTRY_CONTINENT);
		genRandomEntity.setLocations(fileName.LOCATION_LABEL, fileName.LOCATION_DESCRIPTION);
		genRandomEntity.setTime(fileName.TIME_LABEL, fileName.LOCATION_DESCRIPTION);
		genRandomEntity.setEvents(fileName.EVENT_LABEL, fileName.EVENT_DESCRIPTION);
		
		genRelationship.setRelDescriptionList(fileName.RELATIONSHIP_DESCRIPTION);
		personIRIList = new ArrayList<>();
		locIRIList = new ArrayList<>();
		orgIRIList = new ArrayList<>();
		eventIRIList = new ArrayList<>();
		timeIRIList = new ArrayList<>();
		countryIRIList = new ArrayList<>();
//		entitiesIRIList = new ArrayList<>();
	}
	
	private void genEntities(int nEntitites) {
		if(nEntitites > numberOfEntities) {
			Entity entity = null;
			for(int i = 0; i < nEntitites - numberOfEntities; i++) {
				entity = genRandomEntity.genRandomEntity();

				if (entity instanceof Person) {
					personIRIList.add(entitiesIRI.createPersonIRI((Person) entity));
				}
				else if (entity instanceof Organization) {
					orgIRIList.add(entitiesIRI.createOrganizationIRI((Organization) entity));
				}
				else if (entity instanceof Location) {
					locIRIList.add(entitiesIRI.createLocationIRI((Location) entity));
				}
				else if (entity instanceof Time) {
					timeIRIList.add(entitiesIRI.createTimeIRI((Time) entity));
				}
				else if (entity instanceof Event) {
					eventIRIList.add(entitiesIRI.createEventIRI((Event) entity));
				}
				else if (entity instanceof Country) {
					countryIRIList.add(entitiesIRI.createCountryIRI((Country) entity));
				}
//				entitiesIRIList.add(entityIRI.createEntityIRI(entity));
			}
		}
		numberOfEntities = nEntitites;
	}
	
	private void genRelationships(int nRels) {	
		if(nRels > numberOfRelationships) {
			for(int i = 0; i < nRels - numberOfRelationships; i++) {
//				IRI entity1 = entitiesIRIList.get((int) (Math.random() * numberOfEntities + 0));
//				IRI entity2 = entitiesIRIList.get((int) (Math.random() * numberOfEntities + 0));	
//				if (entity1 instanceof PersonIRI && entity2 instanceof PersonIRI) {
//					IRI relationship = entitiesIRI.createRelIRI(genRelationship.genRandomRelDesc());
//					entitiesIRI.addStatement(entity1, relationship, entity2); //full statement with 2 entities and relationship	
//				}
//				} else {
//					IRI relationship = entitiesIRI.createRelIRI(genRelationship.genRandomRelDesc());
//					entitiesIRI.addStatement(entity1, relationship, entity2);
//				}
				// create full statement with 2 entities and 1 corresponding relationship
				int caseNumber = (int) (Math.random() * 0 + 0);
				switch (caseNumber) {
				case 0:
					IRI per1 = personIRIList.get((int) (Math.random() * numberOfEntities + 0));
					IRI per2 = personIRIList.get((int) (Math.random() * numberOfEntities + 0));
					IRI relationship = entitiesIRI.createRelIRI(genRelationship.genRandomRelDesc());
					entitiesIRI.addStatement(per1, relationship, per2);
					break;
				default:
					break;
				}
			}
		}
		numberOfRelationships = nRels;	
	}
	
	public void genDB(int nEntities, int nRels) {
		genEntities(nEntities);	
		genRelationships(nRels);	
	}
	
	public CreateEntitiesIRI getDatabaseConnect() {
		return entitiesIRI;
	}
	
	public void clearStatements() {
		entitiesIRI.clear();
	}
}
