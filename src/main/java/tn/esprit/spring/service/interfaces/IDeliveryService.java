package tn.esprit.spring.service.interfaces;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.Delivery;



public interface IDeliveryService {
	

	public List<Delivery> findWaitingDeliverys(long driverId);
	
	public List<Delivery> findBeingDoneDeliverys(long driverId);
	
	public List<Delivery> findDoneDeliverys(long driverId);

	public Delivery findDeliveryById(long id);
	
	public Delivery findDeliveryByFacture(long factureId);
	
	public List<Delivery> findAllDeliveryByDate(Date date );
	
	public List<Delivery> findAllDeliveryByDeparture(String departure );
	
	public List<Delivery> findAllDeliveryByArrival(String arrival );
	
	public List<Delivery> findAllDeliveryByDriver(long driverId);
	
	
	public List<Delivery> findAllDelivery();
	
	public void deleteAllDelivery();
	
	
	public void createDelivery(long factureId , long driverId,Delivery delivery);
	
	public void removeDriverFromDelivery(long driverId, long deliveryId);
	
	public void deleteDeliveryById(long id);
	

}
