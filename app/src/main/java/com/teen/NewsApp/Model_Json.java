package com.teen.NewsApp;

public class Model_Json {
	String ImageUrlTo;
	String title;
	String description;
	public Model_Json(String ImageUrlTo,String title,String description){
		this.ImageUrlTo = ImageUrlTo;
		this.title = title;
		this.description = description;
	}
	public String getImage(){
		return this.ImageUrlTo;
	}
	public String getTitle(){
		return this.title;
	}
	public String getDescription(){
		return this.description;
	}
}