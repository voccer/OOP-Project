package com.virtuoso.connectdb;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import com.virtuoso.entity.Event;

public class EventIRI extends VirtuosoRepoSchema{
	public EventIRI() {
		
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
}
