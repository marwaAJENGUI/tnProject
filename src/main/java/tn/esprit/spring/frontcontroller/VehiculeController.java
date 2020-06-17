package tn.esprit.spring.frontcontroller;

import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import tn.esprit.spring.entity.Vehicule;
import tn.esprit.spring.entity.VehiculeType;
import tn.esprit.spring.service.interfaces.IVehiculeService;

@Scope(value = "session")
@Controller(value = "vehiculeFController")
@ELBeanName(value = "vehiculeFController")
public class VehiculeController {

	@Autowired
	IVehiculeService iVehiculeService;

	private String matricule;
	private String brand;
	private String location;
	
	private Long vehiculeIdToBeUpdated;
	private List<Vehicule> vehicules;
	
	
	
	private long selectVehiculeId;
	private long selectDriverId;
	
	private VehiculeType VType;
	


	public void clear() {
		setMatricule(null);
		setBrand(null);
		setLocation(null);
	}

	public String back() {
		clear();
		return "/admin/Vehicule/allVehicules.jsf?faces-redirect=true";
	}

	public String addVehicule() {
		String navigateTo = "null";
		iVehiculeService.addVehicule(new Vehicule(matricule, brand, location,VType));
		clear();
		navigateTo = "/admin/Vehicule/addVehicule.jsf?faces-redirect=true";
		return navigateTo;
	}

	public String deleteVehiculeByID(int vehiculeId) {
		String navigateTo = "null";
		iVehiculeService.deleteVehiculeByID(vehiculeId);
		navigateTo = "/admin/Vehicule/allVehicules.jsf?faces-redirect=true";
		return navigateTo;
	}

	public String updateVehicule() {
		Vehicule v = new Vehicule(vehiculeIdToBeUpdated, matricule, brand, location,VType);
		iVehiculeService.updateVehicule(v);
		clear();
		return "/admin/Vehicule/allVehicules.jsf?faces-redirect=true";

	}

	public String copyVehiculeInfo(Vehicule vehicule) {
		this.setVehiculeIdToBeUpdated(vehicule.getId());
		this.setMatricule(vehicule.getMatricule());
		this.setBrand(vehicule.getBrand());
		this.setLocation(vehicule.getLocation());
		this.setVType(vehicule.getType());;
		return "/admin/Vehicule/updateVehicule.jsf?faces-redirect=true";
	}
	
	
	

	public String affectVehiculeToDriver() {
		iVehiculeService.affectVehiculeToDriver(selectVehiculeId,selectDriverId);
		return "/admin/Vehicule/allVehicules.jsf?faces-redirect=true";
	}
	
	public String unAffectVehiculeFromDriver() {
		
		iVehiculeService.unAffectVehiculeFromDriver(selectVehiculeId,selectDriverId);
		return "/admin/Vehicule/allVehicules.jsf?faces-redirect=true";
	}
	
	
	
	public List<Vehicule> getVehicules() {
		vehicules = iVehiculeService.findAllVehicules();
		return vehicules;
	}
	

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getVehiculeIdToBeUpdated() {
		return vehiculeIdToBeUpdated;
	}

	public void setVehiculeIdToBeUpdated(Long vehiculeIdToBeUpdated) {
		this.vehiculeIdToBeUpdated = vehiculeIdToBeUpdated;
	}


	public void setVehicules(List<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}

	public VehiculeType getVType() {
		return VType;
	}

	public void setVType(VehiculeType vType) {
		VType = vType;
	}

	public long getSelectVehiculeId() {
		return selectVehiculeId;
	}


	public long getSelectDriverId() {
		return selectDriverId;
	}

	public void setSelectDriverId(long selectDriverId) {
		this.selectDriverId = selectDriverId;
	}

	public void setSelectVehiculeId(long selectVehiculeId) {
		this.selectVehiculeId = selectVehiculeId;
	}
}
