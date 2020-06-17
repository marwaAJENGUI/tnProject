package tn.esprit.spring.service.interfaces;

import java.io.IOException;
import java.util.List;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import tn.esprit.spring.entity.Driver;
import tn.esprit.spring.entity.Location;
import tn.esprit.spring.entity.Vehicule;

public interface IDriverService {

	
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
	
	public void acceptDelivery(long deliveryId);
	
	public void markDeliveryAsFinished(long deliveryId);
	
	public Location findDriverLocation(long driverId) throws IOException, GeoIp2Exception;
	

}
