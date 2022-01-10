package com.qa.whiskey.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
		Whiskey result = new Whiskey(2L, "Scotch Whisky", "Johnnie Walker", "Red Label", 80L);
		
		String entryAsJSON = this.mapper.writeValueAsString(entry);
		String resultAsJSON = this.mapper.writeValueAsString(result);
		
		mvc.perform(post("/whiskey/buyABottle")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entryAsJSON)).andExpect(status()
				.isCreated()).andExpect(content().json(resultAsJSON));
	}
	
	
}
