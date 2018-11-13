package com.virtuoso.connectdb;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.repository.RepositoryResult;

import com.virtuoso.entity.Country;
import com.virtuoso.entity.Entity;
import com.virtuoso.entity.Event;
import com.virtuoso.entity.Location;
import com.virtuoso.entity.Organization;
import com.virtuoso.entity.Person;
import com.virtuoso.entity.Time;

public class CreateEntitiesIRI extends VirtuosoRepoSchema {	
	public CreateEntitiesIRI() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IRI createPersonIRI(Person person) {
		IRI personIRI = valueFactory.createIRI(PERSON.toString(), Integer.toString(person.getPersonId()));
		
		Literal label = valueFactory.createLiteral(person.getLabel());
		Literal desc = valueFactory.createLiteral(person.getDescription());
		Literal link = valueFactory.createLiteral(person.getLink());
		Literal date = valueFactory.createLiteral(person.getDate());
		Literal status = valueFactory.createLiteral(person.getStatus());
		
		//add new statement to database
		conn.add(personIRI, RDF.TYPE, PERSON);
		conn.add(personIRI, LABEL, label);
		conn.add(personIRI, DESCRIPTION, desc);
		conn.add(personIRI, DATE, date);
		conn.add(personIRI, LINK, link);
		conn.add(personIRI, STATUS, status);
		
		return personIRI;
	}
	
	public IRI createCountryIRI(Country country) {
		IRI countryIRI = valueFactory.createIRI(COUNTRY.toString(), Integer.toString(country.getCountryId()));
		
		Literal label = valueFactory.createLiteral(country.getLabel());
		Literal desc = valueFactory.createLiteral(country.getDescription());
		Literal link = valueFactory.createLiteral(country.getLink());
		Literal date = valueFactory.createLiteral(country.getDate());
		Literal continent = valueFactory.createLiteral(country.getContinent());
		
		//add new statement to database
		conn.add(countryIRI, RDF.TYPE, COUNTRY);
		conn.add(countryIRI, LABEL, label);
		conn.add(countryIRI, DESCRIPTION, desc);
		conn.add(countryIRI, DATE, date);
		conn.add(countryIRI, LINK, link);
		conn.add(countryIRI, STATUS, continent);
		
		return countryIRI;
	}
	
	public IRI createEventIRI(Event event) {
		IRI eventIRI = valueFactory.createIRI(PERSON.toString(), Integer.toString(event.getEventId()));
		
		Literal label = valueFactory.createLiteral(event.getLabel());
		Literal desc = valueFactory.createLiteral(event.getDescription());
		Literal link = valueFactory.createLiteral(event.getLink());
		Literal date = valueFactory.createLiteral(event.getDate());
	
		//add new statement to database
		conn.add(eventIRI, RDF.TYPE, EVENT);
		conn.add(eventIRI, LABEL, label);
		conn.add(eventIRI, DESCRIPTION, desc);
		conn.add(eventIRI, DATE, date);
		conn.add(eventIRI, LINK, link);
		
		return eventIRI;
	}
	
	public IRI createOrganizationIRI(Organization organization) {
		IRI orgIRI = valueFactory.createIRI(ORGANIZATION.toString(), Integer.toString(organization.getOrganizationId()));
		
		Literal label = valueFactory.createLiteral(organization.getLabel());
		Literal desc = valueFactory.createLiteral(organization.getDescription());
		Literal link = valueFactory.createLiteral(organization.getLink());
		Literal date = valueFactory.createLiteral(organization.getDate());
		Literal headquarter = valueFactory.createLiteral(organization.getHeadquarter());
		
		//add new statement to database
		conn.add(orgIRI, RDF.TYPE, ORGANIZATION);
		conn.add(orgIRI, LABEL, label);
		conn.add(orgIRI, DESCRIPTION, desc);
		conn.add(orgIRI, DATE, date);
		conn.add(orgIRI, LINK, link);
		conn.add(orgIRI, HEADQUARTER, headquarter);
		
		return orgIRI;
	}
	
	public IRI createTimeIRI(Time time) {
		IRI timeIRI = valueFactory.createIRI(TIME.toString(), Integer.toString(time.getTimeId()));
		
		Literal label = valueFactory.createLiteral(time.getLabel());
		Literal desc = valueFactory.createLiteral(time.getDescription());
		Literal link = valueFactory.createLiteral(time.getLink());
		Literal date = valueFactory.createLiteral(time.getDate());
		
	
		//add new statement to database
		conn.add(timeIRI, RDF.TYPE, TIME);
		conn.add(timeIRI, LABEL, label);
		conn.add(timeIRI, DESCRIPTION, desc);
		conn.add(timeIRI, DATE, date);
		conn.add(timeIRI, LINK, link);
		
		return timeIRI;
	}
	
	public IRI createLocationIRI(Location location) {
		IRI locIRI = valueFactory.createIRI(LOCATION.toString(), Integer.toString(location.getLocationId()));
		
		Literal label = valueFactory.createLiteral(location.getLabel());
		Literal desc = valueFactory.createLiteral(location.getDescription());
		Literal link = valueFactory.createLiteral(location.getLink());
		Literal date = valueFactory.createLiteral(location.getDate());
	
		//add new statement to database
		conn.add(locIRI, RDF.TYPE, LOCATION);
		conn.add(locIRI, LABEL, label);
		conn.add(locIRI, DESCRIPTION, desc);
		conn.add(locIRI, DATE, date);
		conn.add(locIRI, LINK, link);
		
		return locIRI;
	}
	
	public IRI createEntityIRI(Entity entity) {
		if(entity instanceof Person) {
			return createPersonIRI((Person) entity); 
		}
		else if(entity instanceof Organization) {
			return createOrganizationIRI((Organization) entity);
		}
		else if(entity instanceof Location) {
			return createLocationIRI((Location) entity);
		}
		else if(entity instanceof Country) {
			return createCountryIRI((Country) entity);
		}
		else if(entity instanceof Time) {
			return createTimeIRI((Time) entity);
		}
		else if(entity instanceof Event) {
			return createEventIRI((Event) entity);
		}
		return null;
	}
	
	public IRI createRelIRI(String relDesc) {
		IRI relIRI = valueFactory.createIRI(RELATIONSHIP.toString(), relDesc);
		
		return relIRI;
	}
	
	public void addStatement(IRI entity1, IRI rel, IRI entity2) {
		conn.add(entity1, rel, entity2);
	}
	
	public long queryStatementTime(IRI subject, IRI predicate, IRI object, Resource context) {
		long startTime = System.currentTimeMillis();
		RepositoryResult<Statement> statements = conn.getStatements(subject, predicate, object, context);
	
		long endTime = System.currentTimeMillis();
		statements.close();
		return endTime - startTime;
	}
}
