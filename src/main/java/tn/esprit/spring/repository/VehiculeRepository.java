package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import tn.esprit.spring.entity.Vehicule;



public interface VehiculeRepository extends CrudRepository<Vehicule, Long> {
	
	
	
	@Query("select v from Vehicule v where v.location=:location")
	public List<Vehicule> findVehiculeByLocation(@Param("location") String location);
	
	
	
    @Modifying
    @Transactional
    @Query("DELETE from Vehicule")
    public void deleteAllVehicules();
    
	@Query("SELECT count(*) FROM Vehicule")
    public int numberOfVehicules();

}
