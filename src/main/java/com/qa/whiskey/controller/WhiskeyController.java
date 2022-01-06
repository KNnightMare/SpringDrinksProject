package com.qa.whiskey.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.whiskey.domain.Whiskey;
import com.qa.whiskey.service.WhiskeyService;

@RestController
@RequestMapping("/whiskey")
public class WhiskeyController {
	
	private WhiskeyService service;
	
	private WhiskeyController(WhiskeyService service) {
			this.service = service;
	}
	
	//Create
	@PostMapping("/buyABottle")
	public ResponseEntity<Whiskey> buyAWhiskey(@RequestBody Whiskey whiskey) {
		return new ResponseEntity<Whiskey>(this.service.create(whiskey), HttpStatus.CREATED);
	}
	
	//Read All
	@GetMapping("/justLookAtThemAll")
	public ResponseEntity<List<Whiskey>> lookAtThemAll() {
		return new ResponseEntity<List<Whiskey>>(this.service.getAll(), HttpStatus.OK);
	}
	
	//Read By ID
	@GetMapping("/lookAtOnlyOne/{id}")
	public ResponseEntity<Whiskey> onlyOne(@PathVariable long id) {
		return new ResponseEntity<Whiskey>(this.service.getById(id), HttpStatus.OK);
	}
	
	//Update
	@PutMapping("/switchForAnotherOne/{id}")
	public ResponseEntity<Whiskey> getTheNewOne(@PathVariable long id, @RequestBody Whiskey whiskey) {
		return new ResponseEntity<Whiskey>(this.service.update(id, whiskey), HttpStatus.ACCEPTED);
	}
	
	//Delete
	@DeleteMapping("/drinkOne/{id}")
	public ResponseEntity<Boolean> drinkOneBottle(@PathVariable long id) {
		//Ternary Operator
		return (this.service.delete(id)) ? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT) : new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
	}
}
