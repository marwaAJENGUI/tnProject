package tn.esprit.spring.sevice.impl;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Delivery;
import tn.esprit.spring.entity.Driver;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Status;
import tn.esprit.spring.entity.VehiculeType;
import tn.esprit.spring.repository.DeliveryRepository;
import tn.esprit.spring.repository.DriverRepository;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.service.interfaces.IDeliveryService;

@Service
public class DeliveryService implements IDeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepo;
	
	@Autowired
	private FactureRepository factureRepo;
	
	@Autowired
	private DriverRepository driverRepo;
	
	@Autowired
	private DriverService driverService;
	
	
	@Override
	public List<Delivery> findWaitingDeliverys(long driverId) {
		return deliveryRepo.findWaitingDeliverys(driverId);
	}
	
	@Override
	public List<Delivery> findBeingDoneDeliverys(long driverId) {
		return deliveryRepo.findBeingDoneDeliverys(driverId);
	}
	
	@Override
	public List<Delivery> findDoneDeliverys(long driverId) {
		return deliveryRepo.findDoneDeliverys(driverId);
	}

	@Override
	public Delivery findDeliveryById(long id) {
		return deliveryRepo.findById(id).get();
	}
	
	@Override
	public Delivery findDeliveryByFacture(long factureId) {
		return deliveryRepo.findDeliveryByFacture(factureId);
	}
	


	@Override
	public List<Delivery> findAllDeliveryByDate(Date date) {
		return deliveryRepo.findAllDeliveryByDate(date);
	}

	@Override
	public List<Delivery> findAllDeliveryByDeparture(String departure) {
		return deliveryRepo.findAllDeliveryByDeparture(departure);
	}

	@Override
	public List<Delivery> findAllDeliveryByArrival(String arrival) {
		return deliveryRepo.findAllDeliveryByArrival(arrival);
	}
	
	@Override
	public List<Delivery> findAllDeliveryByDriver(long driverId) {
		return deliveryRepo.findAllDeliveryByDriver(driverId);
	}
	
	

	@Override
	public List<Delivery> findAllDelivery() {
		return (List<Delivery>) deliveryRepo.findAll();
	}

	@Override
	public void deleteAllDelivery() {
		deliveryRepo.deleteAllDelivery();	
	}
	
	
	@Override
	public void deleteDeliveryById(long id) {
		 deliveryRepo.deleteById(id);
	}
	
	@Override
	@Transactional
	public void createDelivery(long factureId, long driverId, Delivery delivery) {
		
		System.out.println("Entring Create Delivery **************");
		int flag = 0;
		int carFlag =1;
		Facture facture = factureRepo.findById(factureId).get();
		
		System.out.println("Facture  **************"+facture);
		Driver driver = driverRepo.findById(driverId).get();
		
		delivery.setDeparture(delivery.getDeparture());
		delivery.setArrival(delivery.getArrival());
		delivery.setDate(delivery.getDate());
		delivery.setStatus(Status.WAITING);
		
		Delivery deliveryCreated = deliveryRepo.save(delivery);
		Delivery factureExistInDelivery = findDeliveryByFacture(factureId);
		if (factureExistInDelivery ==null) {
		if (driver.isAvailable()) {	
			deliveryCreated.setFacture(facture);
			int vehiculeNb = driver.getVehiculeList().size();
			
			// BASKET WEIGHT IS MORE THAN 70KG
			
			if (deliveryCreated.getFacture().getPanierDetail().getBasketWeight() > 700000) {
				for (int index = 0; index < vehiculeNb; index++) {
					VehiculeType vehiculeType = driver.getVehiculeList().get(index).getType();

					if (vehiculeType.equals(VehiculeType.CAR) || vehiculeType.equals(VehiculeType.TRUCK)) {
						driverService.affectDriverToDelivery(driverId, deliveryCreated.getId());
						flag = 1;
						FacesMessage facesMessage = new FacesMessage(driver.getFirstName() +"  is affacted to delivery with Succes !");
						FacesContext.getCurrentInstance().addMessage("form:mybutton", facesMessage);
						break;
					}
				}
				if (flag == 0) {
					// ===> HEAVY BASKET AND THE DRIVER DOES NOT HAVE THE PROPRIETE CAR
					carFlag=0;
					deliveryRepo.deleteById(deliveryCreated.getId());
					FacesMessage facesMessage = new FacesMessage("  HEAVY BASKET AND "+driver.getFirstName().toUpperCase()+" DOES NOT HAVE THE PROPRIETE CAR !");
					FacesContext.getCurrentInstance().addMessage("form:mybutton", facesMessage);}
				
			} else if (deliveryCreated.getFacture().getPanierDetail().getBasketWeight() < 700000) {
				for (int index = 0; index < vehiculeNb; index++) {
					VehiculeType vehiculeType = driver.getVehiculeList().get(index).getType();
					if (vehiculeType.equals(VehiculeType.BICYCLE) || vehiculeType.equals(VehiculeType.MOTORBIKE)) {
						driverService.affectDriverToDelivery(driverId, deliveryCreated.getId());
						flag = 1;
						FacesMessage facesMessage = new FacesMessage(" "+driver.getFirstName() +" is affected to delivery with Success !");
						FacesContext.getCurrentInstance().addMessage("form:mybutton", facesMessage);
						break;
					}
				}
				if (flag == 0) {
					// ==> LIGHT BASKET AND THE DRIVER DOES NOT HAVE THE PROPRIETE CAR
					carFlag=0;
					deliveryRepo.deleteById(deliveryCreated.getId());
					FacesMessage facesMessage = new FacesMessage("  LIGHT BASKET AND "+driver.getFirstName().toUpperCase()+" DOES NOT HAVE THE PROPRIETE CAR !");
					FacesContext.getCurrentInstance().addMessage("form:mybutton", facesMessage);}
			}}
		// ==> CANNOT CREATE DELIVERY : DRIVER IS NOT AVAILABLE
								if (carFlag==1 && flag ==0) {
						deliveryRepo.deleteById(deliveryCreated.getId());
						FacesMessage facesMessage = new FacesMessage("DRIVER IS NOT AVAILABLE !");
						FacesContext.getCurrentInstance().addMessage("form:mybutton", facesMessage);}
					
					
		} else if (flag == 0 && carFlag==1) {
			// CANNOT CREATE DELIVERY : DRIVER IS NOT AVAILABLE OR DELIVERY ISSUE!
		deliveryRepo.deleteById(deliveryCreated.getId());
		FacesMessage facesMessage = new FacesMessage("FACTURE ID IS AlREADY AFFECTED TO A DRIVER !");
		FacesContext.getCurrentInstance().addMessage("form:mybutton", facesMessage);}
		
	}

	// A voir on peut utiliser le cascade.Delete pour mettre le facture et driver automatique à null pour qu'on puisse supprimer un delivery 

	@Override
	@Transactional
	public void removeDriverFromDelivery(long driverId, long deliveryId) {
		Driver driver = driverRepo.findById(driverId).get();
		Delivery delivery = deliveryRepo.findById(deliveryId).get();

		int deliveryNb = driver.getDeliveryList().size();
		for(int index = 0; index < deliveryNb; index++){
			if(driver.getDeliveryList().get(index).getId() == deliveryId){
				driver.getDeliveryList().remove(index);
				delivery.setDriver(null);				
				break;
			}
		}
	}


}
