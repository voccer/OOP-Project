package com.virtuoso.connectdb;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import com.virtuoso.entity.Organization;

public class OrganizationIRI extends VirtuosoRepoSchema{
	public OrganizationIRI() {
		
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
}
