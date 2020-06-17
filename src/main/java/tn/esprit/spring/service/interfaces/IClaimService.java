package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Claim;


public interface IClaimService {
	
	
	// CRUD Driver 
	public Claim addClaim(Claim claim);
	
	public Claim findClaimById(long id);
	
	public void deleteClaimByID(long id);
	
	
	
	public List<Claim> findAllClaims();

}
