package com.qa.whiskey.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

}
