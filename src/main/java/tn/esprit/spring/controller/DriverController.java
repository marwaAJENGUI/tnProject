package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.controller.interfaces.IDriverController;
import tn.esprit.spring.entity.Driver;
import tn.esprit.spring.entity.Vehicule;
import tn.esprit.spring.sevice.impl.DriverService;



@RestController

public class DriverController implements IDriverController {

	
	@Autowired
	private DriverService driverService;

	
	
	// CRUD Driver  
	
	
// http://localhost:8081/SpringMVC/servlet/addDriver
//	{"firstName": "Oussema","lastName": "Saadouli","phoneNumber": "23181323","salary": 1800.0}
	
	@Override
	@PostMapping("/addDriver")
	@ResponseBody
	public Driver addDriver(@RequestBody Driver driver) {
		 return driverService.addDriver(driver);
	}
	
	
//	http://localhost:8081/SpringMVC/servlet/findDriver/1
	@Override
	@GetMapping("/findDriver/{id}")
	@ResponseBody
	public Driver findDriverById(@PathVariable("id") long id) {
		return driverService.findDriverById(id);
	}

	
//	http://localhost:8081/SpringMVC/servlet/updateDriver
//	{"firstName": "Ahmed","lastName": "Zayani","phoneNumber": "2454468","available": true,"salary": 1500.0}
	@Override
	@PutMapping("/updateDriver")
	@ResponseBody
	public Driver updateDriver(@RequestBody Driver driver) {
		return driverService.updateDriver(driver);
	}
	
//	http://localhost:8081/SpringMVC/servlet/deleteDriver/2
	@Override
	@DeleteMapping("/deleteDriver/{id}")
	@ResponseBody
	public void deleteDriverByID(@PathVariable("id") long id) {
		driverService.deleteDriverByID(id);
	}
	
	

	//Methodes de Recherche selon un critère 
	
	
//	http://localhost:8081/SpringMVC/servlet/findDriversByAvailability/true
	@Override
	@GetMapping("/findDriversByAvailability/{available}")
	@ResponseBody
	public List<Driver> findDriversByAvail(@PathVariable("available") boolean available) {
		return driverService.findDriversByAvail(available);
	}
	
//	http://localhost:8081/SpringMVC/servlet/findDriverByFirstName/firstName
	@Override
	@GetMapping("/findDriverByFirstName/{firstName}")
	@ResponseBody
	public Driver findDriverByFirstName(@PathVariable("firstName") String firstName) {
		return driverService.findDriverByFirstName(firstName);
	}

//	http://localhost:8081/SpringMVC/servlet/findDriverBylastName/lastName
	@Override
	@GetMapping("/findDriverBylastName/{lastName}")
	@ResponseBody
	public List<Driver> findDriverByLastName(@PathVariable("lastName") String lastName) {
		return driverService.findDriverByLastName(lastName);
	}

//	http://localhost:8081/SpringMVC/servlet/findSalary/1
	@Override
	@GetMapping("/findSalary/{driverId}")
	@ResponseBody
	public float findSalaireByDriverId(@PathVariable("driverId") long driverId) {
		return driverService.findSalaireByDriverId(driverId);
	}
	
//	http://localhost:8081/SpringMVC/servlet/findDriverVehicules/1
	@Override
	@GetMapping("/findDriverVehicules/{driverId}")
	@ResponseBody
	public List<Vehicule> findDriverVehicules(@PathVariable("driverId") long driverId) {
		return driverService.findDriverVehicules(driverId);
	}
	
	
	

	@Override
	@GetMapping("/findAllDrivers")
	@ResponseBody
	public List<Driver> findAllDrivers() {
		return driverService.findAllDrivers();
	}

	@Override
	@GetMapping("/findAllDriversNames")
	@ResponseBody
	public List<String> findAllDriversNames() {
		return driverService.findAllDriversNames();
	}

	@Override
	@GetMapping("/findNumberOfDrivers")
	@ResponseBody
	public int findNumberOfDrivers() {
		return driverService.findNumberOfDrivers();
	}

	
	@Override
    @DeleteMapping("/deleteAllDrivers") 
	@ResponseBody
	public void deleteAllDrivers() {
		driverService.deleteAllDrivers();
		
	}

//	http://localhost:8081/SpringMVC/servlet/affectDriverToDelivery/1/1
	@Override
	@PutMapping(value = "/affectDriverToDelivery/{driverId}/{deliveryId}") 
	public void affectDriverToDelivery(@PathVariable("driverId")long driverId,@PathVariable("deliveryId") long deliveryId) {
		driverService.affectDriverToDelivery(driverId, deliveryId);
		
	}
//	http://localhost:8081/SpringMVC/servlet/unAffectDriverFromDelivery/1/1
	@Override
	@PutMapping(value = "/unAffectDriverFromDelivery/{driverId}/{deliveryId}") 
	public void unAffectDriverFromDelivery(@PathVariable("driverId")long driverId,@PathVariable("deliveryId") long deliveryId) {
		driverService.unAffectDriverFromDelivery(driverId, deliveryId);
	}
}
