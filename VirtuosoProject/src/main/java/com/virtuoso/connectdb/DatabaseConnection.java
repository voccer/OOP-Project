package com.virtuoso.connectdb;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;

import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.XMLSchema;


import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;

import com.virtuoso.entity.Country;
import com.virtuoso.entity.Entity;
import com.virtuoso.entity.Event;
import com.virtuoso.entity.Location;
import com.virtuoso.entity.Organization;
import com.virtuoso.entity.Person;
import com.virtuoso.entity.Time;

import virtuoso.rdf4j.driver.VirtuosoRepository;

public class DatabaseConnection {
	private static final String URL = "jdbc:virtuoso://localhost:1111/";
	private static final String USERNAME = "dba";
	private static final String PASSWORD = "dba";
	
	//Initialize namespace
	private static String ontologyNamespace = "http://www.example.com/ontology/";
	private static String personNamespace = "http://www.example.com/person/";
	private static String organizationNamespace = "http://www.example.com/organization/";
	private static String locationNamespace = "http://www.example.com/location/";
	private static String countryNamespace = "http://www.example.com/country/";
	private static String timeNamespace = "http://www.example.com/time/";
	private static String eventNamespace = "http://www.example.com/event/";
	private static String relationshipNamespace = "http://www.example.com/relationship/";
	
	//Connect and generate IRI
	private RepositoryConnection connection = null;
	private ValueFactory valueFactory = null;
	
	private IRI labelOntology;
	private IRI descriptionOntology;
	private IRI linkOntology;
	private IRI dateOntology;
	private IRI statusOntology;
	private IRI headquarterOntology;
	
	private IRI personType;
	private IRI organizationType;
	private IRI locationType;
	private IRI countryType;
	private IRI timeType;
	private IRI eventType;
		
	public DatabaseConnection() {
		Repository myRepository = new VirtuosoRepository(URL, USERNAME, PASSWORD);
		connection = myRepository.getConnection();

		valueFactory =  connection.getValueFactory();
		labelOntology = valueFactory.createIRI(ontologyNamespace, "label");
		descriptionOntology = valueFactory.createIRI(ontologyNamespace, "description");

		linkOntology = valueFactory.createIRI(ontologyNamespace, "link");
		dateOntology = valueFactory.createIRI(ontologyNamespace, "date");
		statusOntology = valueFactory.createIRI(ontologyNamespace, "status");
		headquarterOntology = valueFactory.createIRI(ontologyNamespace, "headquarter");
		
		personType = valueFactory.createIRI(ontologyNamespace, "Person");
		organizationType = valueFactory.createIRI(ontologyNamespace, "Organization");
		locationType = valueFactory.createIRI(ontologyNamespace, "Location");
		countryType = valueFactory.createIRI(ontologyNamespace, "Country");
		timeType = valueFactory.createIRI(ontologyNamespace, "Time");
		eventType = valueFactory.createIRI(ontologyNamespace, "Event");
		
	}
	
	public RepositoryConnection getConnection() {
		return connection;
	}
	
	public void closeConnection() {
		if(connection != null) {
			connection.close();
		}
	}
	
	public void clear() {
		if(connection != null) {
			connection.clear();
		}
	}
	
	private IRI insertPerson(Person person) {
		IRI iri = valueFactory.createIRI(personNamespace, Integer.toString(person.getPersonId()));
		Literal label = valueFactory.createLiteral(person.getLabel());
		Literal description = valueFactory.createLiteral(person.getDescription());
		Literal link = valueFactory.createLiteral(person.getLink());
		Literal date = valueFactory.createLiteral(person.getDate(), XMLSchema.DATE);
		Literal status = valueFactory.createLiteral(person.getStatus());
		
		connection.add(iri, RDF.TYPE, personType );
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, linkOntology, link);
		connection.add(iri, dateOntology, date);
		connection.add(iri, statusOntology, status);
		
		return iri;
	}
	
	private IRI insertOrganization(Organization organization) {
		IRI iri = valueFactory.createIRI(organizationNamespace, Integer.toString(organization.getOrganizationId()));
		Literal label = valueFactory.createLiteral(organization.getLabel());
		Literal description = valueFactory.createLiteral(organization.getDescription());
		Literal link = valueFactory.createLiteral(organization.getLink());
		Literal date = valueFactory.createLiteral(organization.getDate(), XMLSchema.DATE);
		Literal headquarter = valueFactory.createLiteral(organization.getHeadquarter());
		
		connection.add(iri, RDF.TYPE, organizationType);
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, linkOntology, link);
		connection.add(iri, dateOntology, date);
		connection.add(iri, headquarterOntology, headquarter);
		
		return iri;
	}
	
	private IRI insertLocation(Location location) {
		IRI iri = valueFactory.createIRI(locationNamespace, Integer.toString(location.getLocationId()));
		Literal label = valueFactory.createLiteral(location.getLabel());
		Literal description = valueFactory.createLiteral(location.getDescription());
		Literal link = valueFactory.createLiteral(location.getLink());
		Literal date = valueFactory.createLiteral(location.getDate(), XMLSchema.DATE);
		
		connection.add(iri, RDF.TYPE, locationType);
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, linkOntology, link);
		connection.add(iri, dateOntology, date);
		
		return iri;
	}
	
	private IRI insertCountry(Country country) {
		IRI iri = valueFactory.createIRI(countryNamespace, ((Integer)country.getCountryId()).toString());
		Literal label = valueFactory.createLiteral(country.getLabel());
		Literal description = valueFactory.createLiteral(country.getDescription());
		Literal link = valueFactory.createLiteral(country.getLink());
		Literal date = valueFactory.createLiteral(country.getDate(), XMLSchema.DATE);
		
		connection.add(iri, RDF.TYPE, countryType);
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, linkOntology, link);
		connection.add(iri, dateOntology, date);
		
		return iri;
	}
	
	private IRI insertTime(Time time) {
		IRI iri = valueFactory.createIRI(timeNamespace, Integer.toString(time.getTimeId()));
		Literal label = valueFactory.createLiteral(time.getLabel());
		Literal description = valueFactory.createLiteral(time.getDescription());
		Literal link = valueFactory.createLiteral(time.getLink());
		Literal date = valueFactory.createLiteral(time.getDate(), XMLSchema.DATE);
		
		connection.add(iri, RDF.TYPE, timeType);
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, linkOntology, link);
		connection.add(iri, dateOntology, date);
		
		return iri;
	}
	
	private IRI insertEvent(Event event) {
		IRI iri = valueFactory.createIRI(eventNamespace, Integer.toString(event.getEventId()));
		Literal label = valueFactory.createLiteral(event.getLabel());
		Literal description = valueFactory.createLiteral(event.getDescription());
		Literal link = valueFactory.createLiteral(event.getLink());
		Literal date = valueFactory.createLiteral(event.getDate(), XMLSchema.DATE);
		
		connection.add(iri, RDF.TYPE, eventType);
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, linkOntology, link);
		connection.add(iri, dateOntology, date);
		
		return iri;
	}
	
	public IRI insertEntity(Entity entity) {
		if(entity instanceof Person) {
			return insertPerson((Person) entity); 
		}
		else if(entity instanceof Organization) {
			return insertOrganization((Organization) entity);
		}
		else if(entity instanceof Location) {
			return insertLocation((Location) entity);
		}
		else if(entity instanceof Country) {
			return insertCountry((Country) entity);
		}
		else if(entity instanceof Time) {
			return insertTime((Time) entity);
		}
		else if(entity instanceof Event) {
			return insertEvent((Event) entity);
		}
		return null;
	}
	
	public IRI createRelationship(String relationshipDescription) {
		return valueFactory.createIRI(relationshipNamespace, relationshipDescription);
	}
	
	public void insertStatement(IRI entity1, IRI relationship, IRI entity2) {
		connection.add(entity1, relationship, entity2);
	}
	
/*	public long querySPARQLTime(String queryString) {
		AGTupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
		long startTime = System.currentTimeMillis();
		
		TupleQueryResult result = tupleQuery.evaluate();
		
		long endTime = System.currentTimeMillis();
		result.close();
		return endTime - startTime;
	}*/
	
	public long queryStatementTime(IRI subject, IRI predicate, IRI object, Resource context) {
		long startTime = System.currentTimeMillis();
		RepositoryResult<Statement> statements = connection.getStatements(subject, predicate, object, context);
	
		long endTime = System.currentTimeMillis();
		//Model model = QueryResults.asModel(statements);
		//Rio.write(model, System.out, RDFFormat.TURTLE);
		statements.close();
		return endTime - startTime;
	}
	

	public IRI getLabelOntology() {
		return labelOntology;
	}

	public IRI getDescriptionOntology() {
		return descriptionOntology;
	}

	public IRI getlinkOntology() {
		return linkOntology;
	}

	public IRI getdateOntology() {
		return dateOntology;
	}

	public IRI getStatusOntoloty() {
		return statusOntology;
	}

	public IRI getHeadquarterOntology() {
		return headquarterOntology;
	}

	public IRI getPersonType() {
		return personType;
	}

	public IRI getOrganizationType() {
		return organizationType;
	}

	public IRI getLocationType() {
		return locationType;
	}

	public IRI getCountryType() {
		return countryType;
	}

	public IRI getTimeType() {
		return timeType;
	}

	public IRI getEventType() {
		return eventType;
	}
	
}

