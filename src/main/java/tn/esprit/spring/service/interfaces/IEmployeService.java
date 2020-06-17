package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Employe;

public interface IEmployeService {
	public Employe getEmployeByEmailAndPassword(String login, String password) ;
	public List<Employe> getAllEmployes();
	public void ajouterEmploye(Employe e);
	public void deleteEmploye(int employeid);
	public void updateEmploye(Employe e);
}
