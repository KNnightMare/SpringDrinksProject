package com.qa.whiskey.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class WhiskeyTest {
	
	@Test
	public void equalsTest() {
		EqualsVerifier.forClass(Whiskey.class).usingGetClass().verify();
	}
	
	@Test
	public void noIdConstructor() {
		Whiskey whiskey = new Whiskey("Tennessee Whiskey", "Jack Daniels", "Gentleman Jack", 86L);
		
		assertNotNull(whiskey.getType());
		assertNotNull(whiskey.getBrand());
		assertNotNull(whiskey.getBlend());
		assertNotNull(whiskey.getProof());
		
		assertEquals(whiskey.getType(), "Tennessee Whiskey");
		assertEquals(whiskey.getBrand(), "Jack Daniels");
		assertEquals(whiskey.getBlend(), "Gentleman Jack");
		assertEquals(whiskey.getProof(), 86L);
	}
	
	@Test
	public void withIdConstructor() {
		Whiskey whiskey = new Whiskey(2L, "Bourbon", "Barrell Bourbon", "Batch 026", 112L);
		
		assertNotNull(whiskey.getId());
		assertNotNull(whiskey.getType());
		assertNotNull(whiskey.getBrand());
		assertNotNull(whiskey.getBlend());
		assertNotNull(whiskey.getProof());
		
		assertEquals(whiskey.getId(), 2L);
		assertEquals(whiskey.getType(), "Bourbon");
		assertEquals(whiskey.getBrand(), "Barrell Bourbon");
		assertEquals(whiskey.getBlend(), "Batch 026");
		assertEquals(whiskey.getProof(), 112L);
	}

	@Test
	public void settersTest() {
		Whiskey whiskey = new Whiskey();
		
		whiskey.setId(3L);
		whiskey.setType("Bourbon");
		whiskey.setBrand("Four Roses");
		whiskey.setBlend("Single Barrel");
		whiskey.setProof(100L);
		
		assertNotNull(whiskey.getId());
		assertNotNull(whiskey.getType());
		assertNotNull(whiskey.getBrand());
		assertNotNull(whiskey.getBlend());
		assertNotNull(whiskey.getProof());
		
		assertEquals(whiskey.getId(), 3L);
		assertEquals(whiskey.getType(), "Bourbon");
		assertEquals(whiskey.getBrand(), "Four Roses");
		assertEquals(whiskey.getBlend(), "Single Barrel");
		assertEquals(whiskey.getProof(), 100L);
	}
}
