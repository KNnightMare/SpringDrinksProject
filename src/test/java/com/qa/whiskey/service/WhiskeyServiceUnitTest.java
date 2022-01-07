package com.qa.whiskey.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.whiskey.domain.Whiskey;
import com.qa.whiskey.repo.WhiskeyRepo;

@SpringBootTest
public class WhiskeyServiceUnitTest {
	
	@Autowired
	private WhiskeyService service;
	
	@MockBean
	private WhiskeyRepo repo;
	
	@Test
	public void createTest() {
		Whiskey input = new Whiskey("Scotch Whiskey", "Chivas Regal", "Chivas Regal 18", 82);
		Whiskey output = new Whiskey(1L,"Scotch Whiskey", "Chivas Regal", "Chivas Regal 18", 82);
		
		Mockito.when(this.repo.save(input)).thenReturn(output);
		assertEquals(output, this.service.create(input));
		
		Mockito.verify(this.repo, Mockito.times(1)).save(input);
	}
}
