package com.qa.whiskey.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
		Mockito.verify(this.repo, Mockito.times(1)).findById(2L);
	}
		
//		@Test
//		public void ifItFailsTest() {
//		Mockito.when(this.repo.findById(3L)).thenReturn(null);
//		assertThat(this.service.getById(3)).isNull();
//	}
	
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
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		assertTrue(this.service.delete(1L));
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
		
	}
	

	@Test
	public void updateTest() {
		Whiskey input = new Whiskey("Tennessee Whisky", "Four Roses", "Purple Rose", 96);
		Whiskey output = new Whiskey(1L, "Tennessee Whisky", "Four Roses", "Purple Rose", 96);
		Optional<Whiskey> existing = Optional.of(new Whiskey(1L,"Scotch Whisky", "Four Roses", "Purple Rose", 96));
		
		Mockito.when(this.repo.findById(1L)).thenReturn(existing);
		Mockito.when(this.repo.saveAndFlush(output)).thenReturn(output);
		assertEquals(output, this.service.update(1L, input));
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(output);
		
		
		
		
		
//		Whiskey currentWhiskey = new Whiskey(8L, "Scotch Whiskey", "Johnnie Walker", "Red Label", 82);
//		Whiskey whiskeyEight = this.repo.findById(8L).get();
//		whiskeyEight.setBlend("Silver Jack");
//		Whiskey updatedWhiskey = this.repo.saveAndFlush(whiskeyEight);
//		Assertions.assertThat(updatedWhiskey.getBlend()).isEqualTo("Silver Jack");
//		Whiskey newWhiskey;
//		whiskeyEight.setBlend("Silver Jack");
//		given(this.repo.findById(8L)).willReturn(Optional.of(whiskeyEight));
	}
}