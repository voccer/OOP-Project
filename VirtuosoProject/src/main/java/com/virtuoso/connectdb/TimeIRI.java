package com.virtuoso.connectdb;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import com.virtuoso.entity.Time;

public class TimeIRI extends VirtuosoRepoSchema{
	public TimeIRI() {
		
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
}
