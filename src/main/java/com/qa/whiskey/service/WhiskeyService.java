package com.qa.whiskey.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.qa.whiskey.domain.Whiskey;
import com.qa.whiskey.repo.WhiskeyRepo;

@Service
public class WhiskeyService implements ServiceMethods <Whiskey> {
	
	private WhiskeyRepo repo;
	
	public WhiskeyService(WhiskeyRepo repo) {
		this.repo = repo;
	}

	@Override
	public Whiskey create(Whiskey whiskey) {
		return this.repo.save(whiskey);
	}
	
	@Override
	public List<Whiskey> getAll() {
		return this.repo.findAll();
	}

	@Override
	public Whiskey getById(long id) {
		Optional<Whiskey> optionalWhiskey = this.repo.findById(id);
		if(optionalWhiskey.isPresent()) {
			return optionalWhiskey.get();
		}
		
		return null;
	}

	@Override
	public Whiskey update(long id, Whiskey whiskey) {
		Optional<Whiskey> existingWhiskey = this.repo.findById(id);
		if(existingWhiskey.isPresent()) {
			Whiskey existing = existingWhiskey.get();
			existing.setType(whiskey.getType());
			existing.setBrand(whiskey.getBrand());
			existing.setBlend(whiskey.getBlend());
			existing.setProof(whiskey.getProof());
			return this.repo.saveAndFlush(existing);
		}
		return null;
	}

	@Override
	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
