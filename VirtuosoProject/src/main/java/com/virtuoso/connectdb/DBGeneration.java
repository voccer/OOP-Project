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

	private GenRandomEntity genRandomEntity = new GenRandomEntity();
	
	private GenRelationship genRelOther = new GenRelationship();
	private GenRelationship genRelPerPer = new GenRelationship();
	private GenRelationship genRelPer_OrgEve = new GenRelationship();
	private GenRelationship genRelCtyCty = new GenRelationship();
	private GenRelationship genRelOrgEve_Time = new GenRelationship();
	private GenRelationship genRelEve_LocCty = new GenRelationship();
	private GenRelationship genRelPerCty_CtyEve = new GenRelationship();
	private GenRelationship genRelLocOrg = new GenRelationship();

	private CreateEntitiesIRI entitiesIRI = new CreateEntitiesIRI();
	
	
	private List<IRI> personIRIList;
	private List<IRI> locIRIList;
	private List<IRI> orgIRIList;
	private List<IRI> eventIRIList;
	private List<IRI> timeIRIList;
	private List<IRI> countryIRIList;
	private List<IRI> entitiesIRIList;
	
	public DBGeneration() throws FileNotFoundException {
		
		genRandomEntity.setEntities(numberOfLinks, numberOfDates);
		genRandomEntity.setPeople(fileName.PERSON_LABEL, fileName.PERSON_DESCRIPTION, fileName.PERSON_STATUS);
		genRandomEntity.setOrganizations(fileName.ORGANIZATION_LABEL, fileName.ORGANIZATION_DESCRIPTION, fileName.ORGANIZATION_HEADQUARTER);
		genRandomEntity.setCountries(fileName.COUNTRY_LABEL, fileName.COUNTRY_DESCRIPTION, fileName.COUNTRY_CONTINENT);
		genRandomEntity.setLocations(fileName.LOCATION_LABEL, fileName.LOCATION_DESCRIPTION);
		genRandomEntity.setTime(fileName.TIME_LABEL, fileName.LOCATION_DESCRIPTION);
		genRandomEntity.setEvents(fileName.EVENT_LABEL, fileName.EVENT_DESCRIPTION);
		
		genRelOther.setRelDescriptionList(fileName.RELATIONSHIP_OTHER);
		genRelPerPer.setRelDescriptionList(fileName.RELATIONSHIP_PERPER);
		genRelPer_OrgEve.setRelDescriptionList(fileName.RELATIONSHIP_PER_ORGEVE);
		genRelCtyCty.setRelDescriptionList(fileName.RELATIONSHIP_CTYCTY);
		genRelOrgEve_Time.setRelDescriptionList(fileName.RELATIONSHIP_ORGEVE_TIM);
		genRelEve_LocCty.setRelDescriptionList(fileName.RELATIONSHIP_EVE_LOCCTY);
		genRelPerCty_CtyEve.setRelDescriptionList(fileName.RELATIONSHIP_PERCTY_CTYEVE);
		genRelLocOrg.setRelDescriptionList(fileName.RELATIONSHIP_LOCORG);
		
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
				IRI entityIRI = entitiesIRI.createEntityIRI(entity);
				entitiesIRIList.add(entityIRI);
				if (entity instanceof Person) {
					personIRIList.add(entityIRI);
				}
				else if (entity instanceof Organization) {
					orgIRIList.add(entityIRI);
				}
				else if (entity instanceof Location) {
					locIRIList.add(entityIRI);
				}
				else if (entity instanceof Time) {
					timeIRIList.add(entityIRI);
				}
				else if (entity instanceof Event) {
					eventIRIList.add(entityIRI);
				}
				else if (entity instanceof Country) {
					countryIRIList.add(entityIRI);
				}
				
			}
		}
		numberOfEntities = nEntitites;
	}
	
	private void genRelationships(int nRels) {	
		if(nRels > numberOfRelationships) {
			for(int i = 0; i < nRels - numberOfRelationships; i++) {
				// create full statement with 2 entities and 1 corresponding relationship
				IRI entity1 = entitiesIRIList.get((int) (Math.random() * numberOfEntities + 0));
				IRI entity2 = entitiesIRIList.get((int) (Math.random() * numberOfEntities + 0));
				if (personIRIList.contains(entity1) && personIRIList.contains(entity2)) {
					IRI relationship = entitiesIRI.createRelIRI(genRelPerPer.genRandomRelDesc());
					entitiesIRI.addStatement(entity1, relationship, entity2);
					break;
				}
				else if (personIRIList.contains(entity1) && (orgIRIList.contains(entity2) || orgIRIList.contains(entity2))) {
					IRI relationship = entitiesIRI.createRelIRI(genRelPer_OrgEve.genRandomRelDesc());
					entitiesIRI.addStatement(entity1, relationship, entity2);
					break;
				}
				else if (countryIRIList.contains(entity1) && countryIRIList.contains(entity2)) {
					IRI relationship = entitiesIRI.createRelIRI(genRelCtyCty.genRandomRelDesc());
					entitiesIRI.addStatement(entity1, relationship, entity2);
					break;
				}
				else if ((orgIRIList.contains(entity1) || eventIRIList.contains(entity1)) && timeIRIList.contains(entity2)) {
					IRI relationship = entitiesIRI.createRelIRI(genRelOrgEve_Time.genRandomRelDesc());
					entitiesIRI.addStatement(entity1, relationship, entity2);
					break;
				}
				else if (eventIRIList.contains(entity1) && (locIRIList.contains(entity2) || countryIRIList.contains(entity2))) {
					IRI relationship = entitiesIRI.createRelIRI(genRelEve_LocCty.genRandomRelDesc());
					entitiesIRI.addStatement(entity1, relationship, entity2);
					break;
				}
				else if ((personIRIList.contains(entity1) || countryIRIList.contains(entity1)) && (countryIRIList.contains(entity2) || eventIRIList.contains(entity2))) {
					IRI relationship = entitiesIRI.createRelIRI(genRelPerCty_CtyEve.genRandomRelDesc());
					entitiesIRI.addStatement(entity1, relationship, entity2);
					break;
				}
				else if (locIRIList.contains(entity1) && orgIRIList.contains(entity2)) {
					IRI relationship = entitiesIRI.createRelIRI(genRelLocOrg.genRandomRelDesc());
					entitiesIRI.addStatement(entity1, relationship, entity2);
					break;
				}
				else {
					IRI relationship = entitiesIRI.createRelIRI(genRelOther.genRandomRelDesc());
					entitiesIRI.addStatement(entity1, relationship, entity2);
					break;
				}
//				int caseNumber = (int) (Math.random() * 7 + 0);
//				switch (caseNumber) {
//				case 0:
//					IRI per1 = personIRIList.get((int) (Math.random() * numberOfEntities + 0));
//					IRI per2 = personIRIList.get((int) (Math.random() * numberOfEntities + 0));
//					IRI relationship = entitiesIRI.createRelIRI(genRelPerPer.genRandomRelDesc());
//					entitiesIRI.addStatement(per1, relationship, per2);
//					break;
//				case 1:
//					
//				case 2:
//					
//				case 3:
//				
//				case 4:
//				
//				case 5:
//				
//				case 6:
//				default:
//					break;
//				}
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
