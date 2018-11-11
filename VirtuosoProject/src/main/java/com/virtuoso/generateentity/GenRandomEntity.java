package com.virtuoso.generateentity;


import java.io.FileNotFoundException;
import java.util.Random;

import com.virtuoso.entity.Entity;

public class GenRandomEntity {
	private static final Random RANDOM = new Random();
	private static final GenEntity ENTITY_GENERATION = new GenEntity();
	private static final GenPerson PERSON_GENERATION = new GenPerson();
	private static final GenOrganization ORGANIZATION_GENERATION = new GenOrganization();
	private static final GenLocation LOCATION_GENERATION = new GenLocation();
	private static final GenTime TIME_GENERATION = new GenTime();
	private static final GenEvent EVENT_GENERATION = new GenEvent();
	private static final GenCountry COUNTRY_GENERATION = new GenCountry();

	public GenRandomEntity() {
	}

	public void setEntityGeneration(int noLink, int noDate) {
		ENTITY_GENERATION.setDateList(noDate);
		ENTITY_GENERATION.setLinkList(noLink);	
	}
	
	public void setPersonGeneration(String personLabelFileName, String personDescriptionFileName, String personStatusFileName) throws FileNotFoundException {
		PERSON_GENERATION.setDescriptionList(personDescriptionFileName);
		PERSON_GENERATION.setLabelList(personLabelFileName);
		PERSON_GENERATION.setStatusList(personStatusFileName);
	}

	public void setOrganizationGeneration(String organizationLabelFileName, String organizationDescriptionFileName,
			String headquarterFileName) throws FileNotFoundException {
		ORGANIZATION_GENERATION.setLabelList(organizationLabelFileName);
		ORGANIZATION_GENERATION.setDescriptionList(organizationDescriptionFileName);
		ORGANIZATION_GENERATION.setHeadquarterList(headquarterFileName);
	}

	public void setLocationGeneration(String locationLabelFileName, String locationDescriptionFileName) throws FileNotFoundException {
		LOCATION_GENERATION.setDescriptionList(locationDescriptionFileName);
		LOCATION_GENERATION.setLabelList(locationLabelFileName);
	}

	public void setTimeGeneration(String timeLabelFileName, String timeDescriptionFileName) throws FileNotFoundException {
		TIME_GENERATION.setDescriptionList(timeDescriptionFileName);
		TIME_GENERATION.setLabelList(timeLabelFileName);
	}

	public void setEventGeneration(String eventLabelFileName, String eventDescriptionFileName) throws FileNotFoundException {
		EVENT_GENERATION.setDescriptionList(eventDescriptionFileName);
		EVENT_GENERATION.setLabelList(eventLabelFileName);
	}
	
	// Thiếu Continent file, bổ sung continent file thì thêm tham số vào đây.
	
	public void setCountryGeneration(String countryLabelFileName, String countryDescriptionFileName, String countryContinentFileName) throws FileNotFoundException {
		COUNTRY_GENERATION.setLabelList(countryLabelFileName);
		COUNTRY_GENERATION.setDescriptionList(countryDescriptionFileName);
		COUNTRY_GENERATION.setContinentList(countryContinentFileName);
	}
	
	public Entity generateRandomEntity() {
		int random = RANDOM.nextInt(6);

		switch (random) {
		case 0:
			return PERSON_GENERATION.genPerson();
		case 1:
			return ORGANIZATION_GENERATION.genOrganization();
		case 2:
			return LOCATION_GENERATION.genLocation();
		case 3:
			return TIME_GENERATION.genTime();
		case 4:
			return EVENT_GENERATION.genEvent();
		case 5:
			return COUNTRY_GENERATION.genCountry();
		default:
			break;
		}
		return null;
	}
}
