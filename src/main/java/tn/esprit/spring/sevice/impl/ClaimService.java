package tn.esprit.spring.sevice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Claim;
import tn.esprit.spring.repository.ClaimRepository;
import tn.esprit.spring.service.interfaces.IClaimService;



@Service
public class ClaimService implements IClaimService {
	
	@Autowired
	private ClaimRepository claimRepo;

	@Override
	public Claim addClaim(Claim claim) {
		claimRepo.save(claim);
		return claim;
	}

	@Override
	public Claim findClaimById(long id) {
		return claimRepo.findById(id).get();
	}


	@Override
	public void deleteClaimByID(long id) {
		claimRepo.deleteById(id);
	}

	@Override
	public List<Claim> findAllClaims() {
		return (List<Claim>) claimRepo.findAll();
	}

}
