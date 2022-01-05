package com.qa.whiskey.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Whiskey {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	@NotNull(message = "Please specify a type, e.g. Tennessee whisky, Scotch whiskey...")
	private String type;
	
	@Column
	@NotNull(message = "Please specify a brand, e.g. Jack Daniels, Johnnie Walker...")
	private String brand;
	
	@Column
	@NotNull(message = "Please specify a blend, e.g. Gentleman Jack, Red Label...")
	private String blend;
	
	@Column
	@NotNull(message = "Please insert proof, i.e. Alcohol by Volume.")
	private long proof;

	//Default Constructor
	public Whiskey() {
		super();
	}

	//For Creating
	public Whiskey(String type, String brand, String blend, long proof) {
		super();
		this.type = type;
		this.brand = brand;
		this.blend = blend;
		this.proof = proof;
	}

	//For Testing
	public Whiskey(long id, String type, String brand, String blend, long proof) {
		super();
		this.id = id;
		this.type = type;
		this.brand = brand;
		this.blend = blend;
		this.proof = proof;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBlend() {
		return blend;
	}

	public void setBlend(String blend) {
		this.blend = blend;
	}

	public long getProof() {
		return proof;
	}

	public void setProof(long proof) {
		this.proof = proof;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, brand, blend, proof);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Whiskey other = (Whiskey) obj;
		return Objects.equals(type, other.type) && Objects.equals(brand, other.brand) && Objects.equals(blend, other.blend)
				&& proof == other.proof;
	}

	@Override
	public String toString() {
		return "Whiskey [id=" + id + ", type=" + type + ", brand=" + brand + ", blend=" + blend + ", proof=" + proof + "]";
	}
	
}
