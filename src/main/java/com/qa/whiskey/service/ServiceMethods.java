package com.qa.whiskey.service;

import java.util.List;

public interface ServiceMethods <T> {
	
	//Create
	T create(T t);
	
	//Read All
	List<T> getAll();
	
	//Read by ID
	T getById(long id);
	
	//Update
	T update(long id, T t);
	
	//Delete
	boolean delete(long id);
	
	

}
