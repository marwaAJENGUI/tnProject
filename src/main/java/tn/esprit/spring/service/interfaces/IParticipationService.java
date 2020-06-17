package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Participation;

public interface IParticipationService {

	String addParticipation(Long eid);
	List<Participation> participationsList();
	List<Participation> myParticipations();
}
