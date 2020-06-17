package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Delivery;




public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
	
	
	
	

    @Modifying
    @Transactional
    @Query("UPDATE Delivery d SET d.status = tn.esprit.spring.entity.Status.BEING_DONE where d.id=:deliveryId")
    public void acceptDelivery(@Param("deliveryId")long deliveryId);
    
    @Modifying
    @Transactional
    @Query("UPDATE Delivery d SET d.status = tn.esprit.spring.entity.Status.DONE where d.id=:deliveryId")
    public void markDeliveryAsFinished(@Param("deliveryId")long deliveryId);
    
	@Query("select d from Delivery d join d.facture f where f.id =:factureId")
	Delivery findDeliveryByFacture(@Param("factureId")long factureId);
	
	@Query("select d from Delivery d join d.driver dr where d.status= tn.esprit.spring.entity.Status.WAITING and dr.id =:driverId")
	List<Delivery>findWaitingDeliverys(@Param("driverId")long driverId);
	
	@Query("select d from Delivery d join d.driver dr where d.status= tn.esprit.spring.entity.Status.BEING_DONE and dr.id =:driverId")
	List<Delivery>findBeingDoneDeliverys(@Param("driverId")long driverId);
	
	@Query("select d from Delivery d join d.driver dr where d.status= tn.esprit.spring.entity.Status.DONE and dr.id =:driverId")
	List<Delivery>findDoneDeliverys(@Param("driverId")long driverId);
	
	@Query("select d from Delivery d where d.departure=:departure")
	public List<Delivery> findAllDeliveryByDeparture(@Param("departure") String departure);
	
	@Query("select d from Delivery d where d.arrival=:arrival")
	public List<Delivery> findAllDeliveryByArrival(@Param("arrival") String arrival);
	
	@Query("select d from Delivery d where d.date=:date")
	public List<Delivery> findAllDeliveryByDate(@Param("date") Date date);
	
	@Query("select d.deliveryList from Driver d where d.id=:id")
    public List<Delivery> findAllDeliveryByDriver(@Param("id") long driverId);
	
	
    @Modifying
    @Transactional
    @Query("DELETE from Delivery")
    public void deleteAllDelivery();
    
    


}
