package com.virtuoso.connectdb;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import com.virtuoso.entity.Person;

public class PersonIRI extends VirtuosoRepoSchema{
	public PersonIRI() {
		
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
}
