package tn.esprit.spring.sevice.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Driver;
import tn.esprit.spring.entity.Vehicule;
import tn.esprit.spring.repository.DriverRepository;
import tn.esprit.spring.repository.VehiculeRepository;
import tn.esprit.spring.service.interfaces.IVehiculeService;

@Service
public class VehiculeService implements IVehiculeService {
	
	@Autowired
	private VehiculeRepository vehiculeRepo;
	
	@Autowired
	private DriverRepository driverRepo;

	// CRUD Vehicule 
	
	
	@Override
	public Vehicule addVehicule(Vehicule vehicule) {
		vehiculeRepo.save(vehicule);
		return vehicule;
	}

	@Override
	public Vehicule findVehiculeById(long id) {
		return vehiculeRepo.findById(id).get();
	}

	@Override
	public Vehicule updateVehicule(Vehicule vehicule) {
		vehicule.setId(vehicule.getId());
		vehicule.setMatricule(vehicule.getMatricule());
		vehicule.setBrand(vehicule.getBrand());
		vehicule.setType(vehicule.getType());
		vehicule.setLocation(vehicule.getLocation());
		vehiculeRepo.save(vehicule);
		return vehicule;
	}

	@Override
	public void deleteVehiculeByID(long id) {
		vehiculeRepo.deleteById(id);
		
	}

	
	//Methodes de Recherhce selon un critère 
	
	@Override
	public List<Vehicule> findVehiculeByLocation(String location) {
		return vehiculeRepo.findVehiculeByLocation(location);
	}

	
	
	
	
	@Override
	public List<Vehicule> findAllVehicules() {
		return (List<Vehicule>) vehiculeRepo.findAll();
	}

	@Override
	public int findNumberOfVehicules() {
		return vehiculeRepo.numberOfVehicules();
	}

	@Override
	public void deleteAllVehicules() {
		vehiculeRepo.deleteAllVehicules();
		
	}

	@Override
	@Transactional	
	public void affectVehiculeToDriver(long vehiculeId, long driverId) {
		Vehicule vehicule = vehiculeRepo.findById(vehiculeId).get();
		
		Driver driver = driverRepo.findById(driverId).get();

		
		if(driver.getVehiculeList() == null){
			List<Vehicule> vehicules = new ArrayList<>();
			vehicules.add(vehicule);
			driver.setVehiculeList(vehicules);
		}else
		{	
			vehicule.setDriver(driver);
			driver.getVehiculeList().add(vehicule);
		}		
	}


	@Override
	@Transactional
	public void unAffectVehiculeFromDriver(long vehiculeId, long driverId) {
		Driver driver = driverRepo.findById(driverId).get();
		Vehicule vehicule = vehiculeRepo.findById(vehiculeId).get();

		int vehiculeNb = driver.getVehiculeList().size();
		for(int index = 0; index < vehiculeNb; index++){
			if(driver.getVehiculeList().get(index).getId() == vehiculeId){
				driver.getVehiculeList().remove(index);
				vehicule.setDriver(null);				
				break;
			}
		}
	}
}
