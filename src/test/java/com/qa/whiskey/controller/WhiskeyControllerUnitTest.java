package com.qa.whiskey.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.whiskey.domain.Whiskey;
import com.qa.whiskey.service.WhiskeyService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WhiskeyControllerUnitTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private WhiskeyService service;
	
	@Test
	public void createTest() throws Exception {
		Whiskey entry = new Whiskey("Scotch Whisky", "Johnnie Walker", "Gold Label", 90L);
		String entryAsJSON = this.mapper.writeValueAsString(entry);
		
		Mockito.when(this.service.create(entry)).thenReturn(entry);
		
		mvc.perform(post("/whiskey/buyABottle")
		.contentType(MediaType.APPLICATION_JSON)
		.content(entryAsJSON)).andExpect(status()
		.isCreated()).andExpect(content().json(entryAsJSON));
	}
	
	@Test
	public void readAllTest () throws Exception {
		List<Whiskey> whiskeyList = new ArrayList<>();
		
		Whiskey whiskeyOne = new Whiskey(1L, "Scotch Whisky", "Johnnie Walker", "Gold Label", 90L);
		Whiskey whiskeyTwo = new Whiskey(2L,"Scotch Whiskey", "Johnnie Walker", "Red Label", 82L);
		Whiskey whiskeyThree = new Whiskey(3L, "Tennessee Whisky", "Jack Daniels", "Gentleman Jack", 86);

		whiskeyList.add(whiskeyOne);
		whiskeyList.add(whiskeyTwo);
		whiskeyList.add(whiskeyThree);
		
		Mockito.when(this.service.getAll()).thenReturn(whiskeyList);
		mvc.perform(get("/whiskey/justLookAtThemAll"))
		.andExpect(status().isOk()).andExpect(jsonPath("$.size()"
		,is(whiskeyList.size())));
	}
	
	@Test
	public void readByIdTest () throws Exception {
		Whiskey newEntry = new Whiskey(1L, "Scotch Whisky", "Johnnie Walker", "Gold Label", 90L);
		String newEntryAsJSON = this.mapper.writeValueAsString(newEntry);
		
		Mockito.when(this.service.getById(1L)).thenReturn(newEntry);
		
		mvc.perform(get("/whiskey/lookAtOnlyOne/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(newEntryAsJSON)).andExpect(status()
				.isOk()).andExpect(content().json(newEntryAsJSON));
	}
	
	@Test
	public void deleteTest () throws Exception {
//		Whiskey newEntry = new Whiskey(1L, "Scotch Whisky", "Johnnie Walker", "Gold Label", 90L);
		
		Mockito.when(this.service.delete(1)).thenReturn(true);
		
		mvc.perform(delete("/whiskey/drinkOne/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
//	@Test
//	public void updateTest() {
//		Mockito.when(null)
//		
//	}
}
