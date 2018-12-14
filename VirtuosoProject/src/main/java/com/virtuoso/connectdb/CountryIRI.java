package com.virtuoso.connectdb;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import com.virtuoso.entity.Country;

public class CountryIRI extends VirtuosoRepoSchema{
	public CountryIRI() {
		
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
}
