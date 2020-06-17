package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Facture;


public interface FactureRepository extends CrudRepository<Facture, Long> {
	
	@Query("select f from Facture f")
	public List<Facture> findFactures();

}
