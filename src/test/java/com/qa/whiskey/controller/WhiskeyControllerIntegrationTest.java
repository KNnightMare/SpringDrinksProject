package com.qa.whiskey.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.whiskey.domain.Whiskey;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:whiskey-schema.sql", "classpath:whiskey-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class WhiskeyControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void createTest() throws Exception {
		Whiskey entry = new Whiskey("Scotch Whisky", "Johnnie Walker", "Red Label", 80L);
		Whiskey result = new Whiskey(4L, "Scotch Whisky", "Johnnie Walker", "Red Label", 80L);
		
		String entryAsJSON = this.mapper.writeValueAsString(entry);
		String resultAsJSON = this.mapper.writeValueAsString(result);
		
		mvc.perform(post("/whiskey/buyABottle")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entryAsJSON)).andExpect(status()
				.isCreated()).andExpect(content().json(resultAsJSON));
		
	}
	
	@Test
	public void readByIdTest () throws Exception {
		Whiskey entry = new Whiskey(1L, "Scotch Whiskey", "Chivas Regal", "Chivas Regal 25", 84);
		String entryAsJSON = this.mapper.writeValueAsString(entry);
		
		
		mvc.perform(get("/whiskey/lookAtOnlyOne/{id}", 1))
				.andExpect(status()
				.isOk()).andExpect(content().json(entryAsJSON));
	}
	
	@Test
	public void findAll() throws Exception {
		List<Whiskey> whiskeyList = new ArrayList<>();
		
		Whiskey whiskeyOne = new Whiskey(2L, "Scotch Whiskey", "Chivas Regal", "Chivas Regal 25", 84);
//		String whiskeyOneAsJSON = this.mapper.writeValueAsString(whiskeyOne);
		Whiskey whiskeyTwo = new Whiskey(3L,"Scotch Whiskey", "Johnnie Walker", "Red Label", 82L);
//		String whiskeyTwoAsJSON = this.mapper.writeValueAsString(whiskeyTwo);
		Whiskey whiskeyThree = new Whiskey(4L, "Tennessee Whisky", "Jack Daniels", "Gentleman Jack", 86);
//		String whiskeyThreeAsJSON = this.mapper.writeValueAsString(whiskeyThree);
		
		whiskeyList.add(whiskeyOne);
		whiskeyList.add(whiskeyTwo);
		whiskeyList.add(whiskeyThree);
		
		
		mvc.perform(get("/whiskey/justLookAtThemAll"))
		.andExpect(status().isOk()).andExpect(jsonPath("$.size()"
		,is(whiskeyList.size())));
		
	}
	
}
