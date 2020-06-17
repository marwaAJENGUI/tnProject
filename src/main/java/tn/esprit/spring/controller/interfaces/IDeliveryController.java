package tn.esprit.spring.controller.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.spring.entity.Delivery;

public interface IDeliveryController {
	
//	public List<Delivery> findDeliveryByStatus(boolean completed);
	public Delivery findDeliveryById(long id);
	public List<Delivery> findAllDeliveryByDate(Date date);
	public List<Delivery> findAllDeliveryByDeparture(String departure);
	public List<Delivery> findAllDeliveryByArrival(String arrival);
	public List<Delivery> findAllDeliveryByDriver(long driverId);
	public List<Delivery> findAllDelivery();
	public void deleteAllDelivery();
	
	public void createDelivery(long factureId,long driverId,@RequestBody Delivery delivery);
	
	public void removeDriverFromDelivery( long driverId , long deliveryId);
	
	

}
