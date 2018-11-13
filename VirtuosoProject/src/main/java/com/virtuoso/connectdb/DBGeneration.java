package com.virtuoso.connectdb;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.rdf4j.model.IRI;

import com.virtuoso.entity.Entity;
import com.virtuoso.generateentity.GenRandomEntity;
import com.virtuoso.relationship.GenRelationship;

public class DBGeneration {
	private static int numberOfEntities = 0;
	private static int numberOfRelationships = 0;
	
	private static int numberOfLinks = 10000;
	private static int numberOfDates = 10000;

	private static ConstantsFileName fileName = new ConstantsFileName();	
	
	private CreateEntitiesIRI entitiesIRI = new CreateEntitiesIRI();
	private GenRandomEntity genRandomEntity = new GenRandomEntity();
	private GenRelationship genRelationship = new GenRelationship();
	
	
	private List<IRI> entityIRIList;
	
	public DBGeneration() throws FileNotFoundException {
		
		genRandomEntity.setEntities(numberOfLinks, numberOfDates);
		genRandomEntity.setPeople(fileName.PERSON_LABEL, fileName.PERSON_DESCRIPTION, fileName.PERSON_STATUS);
		genRandomEntity.setOrganizations(fileName.ORGANIZATION_LABEL, fileName.ORGANIZATION_DESCRIPTION, fileName.ORGANIZATION_HEADQUARTER);
		genRandomEntity.setCountries(fileName.COUNTRY_LABEL, fileName.COUNTRY_DESCRIPTION, fileName.COUNTRY_CONTINENT);
		genRandomEntity.setLocations(fileName.LOCATION_LABEL, fileName.LOCATION_DESCRIPTION);
		genRandomEntity.setTime(fileName.TIME_LABEL, fileName.LOCATION_DESCRIPTION);
		genRandomEntity.setEvents(fileName.EVENT_LABEL, fileName.EVENT_DESCRIPTION);
		
		genRelationship.setRelDescriptionList(fileName.RELATIONSHIP_DESCRIPTION);
		
		//virtuosoReposchema.createSchema();
		
		entityIRIList = new ArrayList<>();
	}
	
	private void genEntities(int nEntitites) {
		if(nEntitites > numberOfEntities) {
			Entity entity = null;
			for(int i = 0; i < nEntitites - numberOfEntities; i++) {
				entity = genRandomEntity.genRandomEntity();
				entityIRIList.add(entitiesIRI.createEntityIRI(entity));
			}
		}
		numberOfEntities = nEntitites;
	}
	
	private void genRelationships(int nRels) {
		
		if(nRels > numberOfRelationships) {
			IRI entity1 = null;
			IRI entity2 = null;
			IRI relationship = null;
			
			for(int i = 0; i < nRels - numberOfRelationships; i++) {
				entity1 = entityIRIList.get((int) (Math.random() * numberOfEntities + 0));
				entity2 = entityIRIList.get((int) (Math.random() * numberOfEntities + 0));
				
				relationship = entitiesIRI.createRelIRI(genRelationship.genRandomRelDesc());
				entitiesIRI.addStatement(entity1, relationship, entity2);
				
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
