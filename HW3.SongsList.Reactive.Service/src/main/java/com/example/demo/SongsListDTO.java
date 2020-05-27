package com.example.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SongsListDTO {
	private String id;
	private String name;
	private String userEmail;
	private String createdTimestamp;
	
	public SongsListDTO() {
		
	}
	
	public SongsListDTO(String id, String name, String userEmail, String createdTimestamp) {
		super();
		this.id = id;
		this.name = name;
		this.userEmail = userEmail;
		this.createdTimestamp = createdTimestamp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(String createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	
	public SongsList toEntity(SongsListDTO dto) throws ParseException {
		SongsList entity = new SongsList();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setUserEmail(dto.getUserEmail());
			
	    Date date=new SimpleDateFormat(GlobalVariables.DATE_FORMAT).parse(this.createdTimestamp);    
		entity.setCreatedTimestamp(date);
		
		return entity;
	}
}