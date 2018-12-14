package com.virtuoso.connectdb;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import com.virtuoso.entity.Location;

public class LocationIRI extends VirtuosoRepoSchema{
	public LocationIRI() {
		
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
}
