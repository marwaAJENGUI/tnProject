package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "vehicule")
public class Vehicule implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String matricule ;
	private String brand;
	@Enumerated(EnumType.STRING)
	private VehiculeType type;
	private String location;
	
	
//	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@ManyToOne
	@JsonIgnore
	private Driver driver ;

	
	public Vehicule() {}
	
	


	public Vehicule(String matricule, String brand, String location ,VehiculeType type) {
		super();
		this.matricule = matricule;
		this.brand = brand;
		this.type = type;
		this.location = location;
	}



	public Vehicule(Long id, String matricule, String brand, String location,VehiculeType type) {
		super();
		this.id = id;
		this.matricule = matricule;
		this.brand = brand;
		this.location = location;
		this.type = type;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public VehiculeType getType() {
		return type;
	}

	public void setType(VehiculeType type) {
		this.type = type;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return brand;
	}
}
