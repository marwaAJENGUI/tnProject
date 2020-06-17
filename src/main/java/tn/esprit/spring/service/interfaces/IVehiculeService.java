package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Vehicule;

public interface IVehiculeService {

	// CRUD Driver
	public Vehicule addVehicule(Vehicule vehicule);

	public Vehicule findVehiculeById(long id);

	public Vehicule updateVehicule(Vehicule vehicule);

	public void deleteVehiculeByID(long id);

	// Methodes de Recherhce selon un critère

	public List<Vehicule> findVehiculeByLocation(String location);
	
	
	
	public List<Vehicule> findAllVehicules();
	
	public int findNumberOfVehicules();
	
	public void deleteAllVehicules();
	
	public void affectVehiculeToDriver(long vehiculeId, long driverId);
	
	public void unAffectVehiculeFromDriver(long vehiculeId, long driverId);
	
	
}
