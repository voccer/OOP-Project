package com.virtuoso.Entity;

public class Entity {
	private String label;
	private String description;
	private String link;
	private String time;
	
	public Entity() {
		
	}
	
	public Entity(String label, String description, String link, String time) {
		this.label = label;
		this.description = description;
		this.link = link;
		this.time = time;
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
