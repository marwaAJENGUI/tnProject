package tn.esprit.spring.controller.interfaces;


import java.util.List;

import tn.esprit.spring.entity.Driver;
import tn.esprit.spring.entity.Vehicule;

public interface IDriverController {
	
	
	// CRUD Driver 
	public Driver addDriver(Driver driver);
	
	public Driver findDriverById(long id);

	public Driver updateDriver(Driver driver);
	
	public void deleteDriverByID(long id);
	
	    
	//Methodes de Recherhce selon un critère 
	
	public List<Driver> findDriversByAvail(boolean available);
	
	public Driver findDriverByFirstName(String firstName);
	
	public List<Driver> findDriverByLastName(String lastName);
	
	public float findSalaireByDriverId(long driverId);
	
	public List<Vehicule> findDriverVehicules(long driverId);
	

	
	
	
	
	public List<Driver> findAllDrivers();
	
	public List<String> findAllDriversNames();
	
	public int findNumberOfDrivers();
		
	public void deleteAllDrivers();
	
	
	
	public void affectDriverToDelivery(long driverId, long deliveryId);
	
	public void unAffectDriverFromDelivery(long driverId, long deliveryId);
	

}
