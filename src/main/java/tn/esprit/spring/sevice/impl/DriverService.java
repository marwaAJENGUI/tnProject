package tn.esprit.spring.sevice.impl;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import tn.esprit.spring.entity.Delivery;
import tn.esprit.spring.entity.Driver;
import tn.esprit.spring.entity.Location;
import tn.esprit.spring.entity.Vehicule;
import tn.esprit.spring.repository.DeliveryRepository;
import tn.esprit.spring.repository.DriverRepository;
import tn.esprit.spring.service.interfaces.IDriverService;

@Service
public class DriverService implements IDriverService {

	@Autowired
	private DriverRepository driverRepo;
	
	@Autowired
	private DeliveryRepository deliveryRepo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	

	

	
	// CRUD Driver 
	
	
	@Override
	public Driver addDriver(Driver driver) {
		
		Driver newdriver = new Driver();
		newdriver.setFirstName(driver.getFirstName());
		newdriver.setLastName(driver.getLastName());
		newdriver.setPassword(bcryptEncoder.encode(driver.getPassword()));
		newdriver.setPhoneNumber(driver.getPhoneNumber());
		newdriver.setSalary(driver.getSalary());
		driverRepo.save(newdriver);
		return driver;
	}

	@Override
	public Driver findDriverById(long id) {
		return driverRepo.findById(id).get();
	}
	
	@Override
	public Driver updateDriver(Driver driver) {
		driver.setId(driver.getId());
		driver.setFirstName(driver.getFirstName());
		driver.setLastName(driver.getLastName());
		driver.setPassword(driver.getPassword());
		driver.setPhoneNumber(driver.getPhoneNumber());
		driver.setSalary(driver.getSalary());
		if (driver.getTaskCompleted() >15)
		driver.setPrime(20);
		driver.setTaskCompleted(driver.getTaskCompleted());
		driver.setAvailable(driver.isAvailable());
		driver.setIp(driver.getIp());
		driverRepo.save(driver);
		
		return driver;
	}
	

	@Override
	public void deleteDriverByID(long id) {
		driverRepo.deleteById(id);
	}
	
	
	//Methodes de Recherhce selon un critère 
	
	
	@Override
	public List<Driver> findDriversByAvail(boolean available) {
		return driverRepo.findDriversByAvail(available);
	}
	

	@Override
	public Driver findDriverByFirstName(String firstName) {
		return driverRepo.getDriverByfirstName(firstName);
	}

	@Override
	public List<Driver> findDriverByLastName(String lastName) {
		return driverRepo.getDriversBylastName(lastName);
	}
	

	@Override
	public float findSalaireByDriverId(long driverId) {

		return driverRepo.findSalaireByDriverId(driverId);
	}
	
	@Override
	public List<Vehicule> findDriverVehicules(long driverId) {
		return driverRepo.findDriverVehicules(driverId);
	}
	
	
	@Override
	public List<Driver> findAllDrivers() {
		return (List<Driver>) driverRepo.findAll();
	}
	
	@Override
	public void deleteAllDrivers() {
		driverRepo.deleteAllDrivers();
	}

	@Override
	public List<String> findAllDriversNames() {
		return driverRepo.driversNames();
	}

	@Override
	public int findNumberOfDrivers() {
		return driverRepo.numberOfDrivers();
	}

	@Override
	@Transactional	
	public void affectDriverToDelivery(long driverId, long deliveryId) {

		Driver driver = driverRepo.findById(driverId).get();
		Delivery delivery = deliveryRepo.findById(deliveryId).get();

		if(driver.getDeliveryList() == null){
			List<Delivery> allDelivery = new ArrayList<>();
			allDelivery.add(delivery);
			driver.setDeliveryList(allDelivery);
		}else
		{	
			delivery.setDriver(driver);
			driver.getDeliveryList().add(delivery);
		
		}		
	}

	@Override
	@Transactional
	public void unAffectDriverFromDelivery(long driverId, long deliveryId) {
		Driver driver = driverRepo.findById(driverId).get();
		Delivery delivery = deliveryRepo.findById(deliveryId).get();

		int deliveryNb = driver.getDeliveryList().size();
		for(int index = 0; index < deliveryNb; index++){
			if(driver.getVehiculeList().get(index).getId() == deliveryId){
				driver.getVehiculeList().remove(index);
				delivery.setDriver(null);				
				break;
			}
		}
	}
	
	@Override
	public void acceptDelivery(long deliveryId) {
		
		Delivery delivery = deliveryRepo.findById(deliveryId).get();
		Driver driver = driverRepo.findById(delivery.getDriver().getId()).get();
		
		driver.setId(driver.getId());
		driver.setFirstName(driver.getFirstName());
		driver.setLastName(driver.getLastName());
		driver.setPhoneNumber(driver.getPhoneNumber());
		driver.setAvailable(false);
		driverRepo.save(driver);
		
		deliveryRepo.acceptDelivery(deliveryId);
		
	}
	
	@Override
	public void markDeliveryAsFinished(long deliveryId) {
		
		Delivery delivery = deliveryRepo.findById(deliveryId).get();
		Driver driver = driverRepo.findById(delivery.getDriver().getId()).get();
		
		driver.setId(driver.getId());
		driver.setFirstName(driver.getFirstName());
		driver.setLastName(driver.getLastName());
		driver.setPhoneNumber(driver.getPhoneNumber());
		driver.setAvailable(true);
		driver.setTaskCompleted(driver.getTaskCompleted()+1);
		driverRepo.save(driver);
		deliveryRepo.markDeliveryAsFinished(deliveryId);
	}
	
	@Override
	public Location findDriverLocation(long driverId) throws IOException, GeoIp2Exception {
//		String ip = "197.1.167.155";
		
		Driver driver = driverRepo.findById(driverId).get();
		
		
		String ip = driver.getIp();
		String dbLocation = "C:\\eclipse-pi-workspace\\ConsomiTounsi619_indiv\\src\\main\\resources\\GeoLite2-City.mmdb";

		File database = new File(dbLocation);
		DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
		InetAddress ipAddress = InetAddress.getByName(ip);
		CityResponse response = dbReader.city(ipAddress);
		String countryName = response.getCountry().getName();
		double latitude = response.getLocation().getLatitude();
		double longitude = response.getLocation().getLongitude();
		String cityName = response.getCity().getName();
//	    String postal = response.getPostal().getCode();
		String state = response.getLeastSpecificSubdivision().getName();
//		String latitudeConverted = String.valueOf(latitude);
//		String longitudeConverted = String.valueOf(longitude);
		Location loc = new Location();
		loc.setCountryName(countryName);
		loc.setState(state);
		loc.setCity(cityName);
		loc.setLatitude((float)latitude);
		loc.setLongitude((float)longitude);
		return loc ; 
	}

}
