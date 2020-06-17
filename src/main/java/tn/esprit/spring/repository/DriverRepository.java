package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Driver;
import tn.esprit.spring.entity.Vehicule;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Long> {
	
	
	
	@Query("select d from Driver d where d.firstName=:firstName")
	public Driver getDriverByfirstName(@Param("firstName") String firstName);
	
	@Query("select d from Driver d where d.lastName=:lastName")
	public List<Driver> getDriversBylastName(@Param("lastName") String lastName);
	
	
	@Query("select d.salary from Driver d where d.id=:id")
    public float findSalaireByDriverId(@Param("id") long driverId);
	
	@Query("select d.vehiculeList from Driver d where d.id=:id")
    public List<Vehicule> findDriverVehicules(@Param("id") long driverId);
	
	
	@Query("select d from Driver d where d.available=:available")
	List<Driver>findDriversByAvail(@Param("available") boolean available);
	
    @Query("SELECT firstName FROM Driver")
    public List<String> driversNames();
   	
    @Modifying
    @Transactional
    @Query("DELETE from Driver")
    public void deleteAllDrivers();
    
    
    
	@Query("SELECT count(*) FROM Driver")
    public int numberOfDrivers();
	
}
