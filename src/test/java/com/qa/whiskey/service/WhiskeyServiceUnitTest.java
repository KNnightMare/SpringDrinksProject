package com.qa.whiskey.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	@Test
	public void findByIdTest() {
		Whiskey output = new Whiskey(2L, "Scotch Whiskey", "Johnnie Walker", "Red Label", 82L);
		
		Mockito.when(this.repo.findById(2L)).thenReturn(Optional.of(output));
		assertEquals(Optional.of(output), Optional.of(this.service.getById(2L)));
		
		//need some help here
		Mockito.when(this.repo.findById(3L)).thenReturn(null);
//		assertThat(this.service.getById(3)).isNull();
	}
	
	@Test
	public void getAllTest() {
	List<Whiskey> whiskeyList = new ArrayList<>();
	
	Whiskey whiskeyOne = new Whiskey(4L,"Scotch Whiskey", "Johnnie Walker", "Red Label", 82L);
	Whiskey whiskeyTwo = new Whiskey(5L, "Tennessee Whisky", "Jack Daniels", "Gentleman Jack", 86);
	whiskeyList.add(whiskeyOne);
	whiskeyList.add(whiskeyTwo);
	
	Mockito.when(this.repo.findAll()).thenReturn(whiskeyList);
	List<Whiskey> foundWhiskeys = this.service.getAll();
	assertNotNull(foundWhiskeys);
	assertEquals(2, foundWhiskeys.size());
	}
	
	@Test
	public void deleteTest() {
		List<Whiskey> whiskeyList = new ArrayList<>();
		
		Whiskey whiskeySix = new Whiskey(6L,"Scotch Whiskey", "Johnnie Walker", "Red Label", 82L);
		Whiskey whiskeySeven= new Whiskey(7L, "Tennessee Whisky", "Jack Daniels", "Gentleman Jack", 86);
		whiskeyList.add(whiskeySix);
		whiskeyList.add(whiskeySeven);

		//Need a little tweak here as well
		this.service.delete(6L);
		assertThat(this.service.getById(6)).isNull();
	}
	
	@Test
	public void updateTest() {
		
	}
}