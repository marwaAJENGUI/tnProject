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

import tn.esprit.spring.controller.interfaces.IVehiculeController;
import tn.esprit.spring.entity.Vehicule;
import tn.esprit.spring.sevice.impl.VehiculeService;


@RestController
public class VehiculeController implements IVehiculeController {
	
	@Autowired
	private VehiculeService vehiculeService;

	
	// CRUD Vehicule  
	
// http://localhost:8081/SpringMVC/servlet/addVehicule
//		{"matricule": "206TN9961","type": "SYMBOLE","location": "ariana"}
	@Override
	@PostMapping("/addVehicule")
	@ResponseBody
	public Vehicule addVehicule(@RequestBody Vehicule vehicule) {
		 return vehiculeService.addVehicule(vehicule);
	}

	
//	http://localhost:8081/SpringMVC/servlet/findVehicule/1
	@Override
	@GetMapping("/findVehicule/{id}")
	@ResponseBody
	public Vehicule findVehiculeById(@PathVariable("id") long id) {
		return vehiculeService.findVehiculeById(id);
	}

//	http://localhost:8081/SpringMVC/servlet/updateVehicule	
//	{"matricule": "206TN9961","type": "SYMBOLE","strength": "23181323","location": "ariana"}
	
	
	@Override
	@PutMapping("/updateVehicule")
	@ResponseBody
	public Vehicule updateVehicule(@RequestBody Vehicule vehicule) {
		return vehiculeService.updateVehicule(vehicule);
	}

	
//	http://localhost:8081/SpringMVC/servlet/deleteVehicule/2
	@Override
	@DeleteMapping("/deleteVehicule/{id}")
	@ResponseBody
	public void deleteVehiculeByID(@PathVariable("id") long id) {
		vehiculeService.deleteVehiculeByID(id);
		
	}

	
	//Methodes de Recherche selon un critère 
	
	
//	http://localhost:8081/SpringMVC/servlet/findVehiculeByLocation/ariana
	@Override
	@GetMapping("/findVehiculeByLocation/{location}")
	@ResponseBody
	public List<Vehicule> findVehiculeByLocation(@PathVariable("location") String location) {
		return vehiculeService.findVehiculeByLocation(location);
	}

	@Override
	@GetMapping("/findAllVehicules")
	@ResponseBody
	public List<Vehicule> findAllVehicules() {
		return vehiculeService.findAllVehicules();
	}

	@Override
	@GetMapping("/findNumberOfVehicules")
	@ResponseBody
	public int findNumberOfVehicules() {
		return vehiculeService.findNumberOfVehicules();
	}

	@Override
    @DeleteMapping("/deleteAllVehicules") 
	@ResponseBody
	public void deleteAllVehicules() {
		vehiculeService.deleteAllVehicules();
	}

//	http://localhost:8081/SpringMVC/servlet/affectVehiculeToDriver/1/1
	@Override
	@PutMapping(value = "/affectVehiculeToDriver/{vehiculeId}/{driverId}") 
	public void affectVehiculeToDriver(@PathVariable("vehiculeId")long vehiculeId, @PathVariable("driverId")long driverId) {
		vehiculeService.affectVehiculeToDriver(vehiculeId, driverId);
		
	}

//	http://localhost:8081/SpringMVC/servlet/unAffectVehiculeFromDriver/1/1
	@Override
	@PutMapping(value = "/unAffectVehiculeFromDriver/{vehiculeId}/{driverId}") 
	public void unAffectVehiculeFromDriver(@PathVariable("vehiculeId")long vehiculeId, @PathVariable("driverId")long driverId) {
		vehiculeService.unAffectVehiculeFromDriver(vehiculeId, driverId);	
	}

}
