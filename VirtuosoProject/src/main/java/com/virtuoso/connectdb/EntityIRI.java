package com.virtuoso.connectdb;

import org.eclipse.rdf4j.model.IRI;

import com.virtuoso.entity.Country;
import com.virtuoso.entity.Entity;
import com.virtuoso.entity.Event;
import com.virtuoso.entity.Location;
import com.virtuoso.entity.Organization;
import com.virtuoso.entity.Person;
import com.virtuoso.entity.Time;

public class EntityIRI {
	public EntityIRI() {
		
	}
	
	public IRI createEntityIRI(Entity entity) {
		if (entity instanceof Person) {
			return new PersonIRI().createPersonIRI((Person) entity);
		}
		else if (entity instanceof Organization) {
			return new OrganizationIRI().createOrganizationIRI((Organization) entity);
		}
		else if (entity instanceof Country) {
			return new CountryIRI().createCountryIRI((Country) entity);
		}
		else if (entity instanceof Event) {
			return new EventIRI().createEventIRI((Event) entity);
		}
		else if (entity instanceof Time) {
			return new TimeIRI().createTimeIRI((Time) entity);
		}
		else if (entity instanceof Location) {
			return new LocationIRI().createLocationIRI((Location) entity);
		}
		else {
			return null;
		}
	}
}
