package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.controller.interfaces.IDeliveryController;
import tn.esprit.spring.entity.Delivery;
import tn.esprit.spring.sevice.impl.DeliveryService;


@RestController
public class DeliveryController implements IDeliveryController {

	
	@Autowired
	private DeliveryService deliveryService;
	
	
//	http://localhost:8081/SpringMVC/servlet/findDeliveryByStatus/false
//	@Override
//	@GetMapping("/findDeliveryByStatus/{completed}")
//	@ResponseBody
//	public List<Delivery> findDeliveryByStatus(@PathVariable("completed") boolean completed) {
//		return deliveryService.findDeliveryByStatus(completed);	
//	}
	
	
	@GetMapping("/findWaitingDeliverys/{driverId}")
	@ResponseBody
	public List<Delivery> findDeliveryByStatus(@PathVariable long driverId) {
		return deliveryService.findWaitingDeliverys(driverId);	
	}


//	http://localhost:8081/SpringMVC/servlet/findDelivery/1
	@Override
	@GetMapping("/findDelivery/{id}")
	@ResponseBody
	public Delivery findDeliveryById(@PathVariable long id) {
		return deliveryService.findDeliveryById(id);
	}

//	http://localhost:8081/SpringMVC/servlet/findAllDeliveryByDate/date
	@Override
	@GetMapping("/findAllDeliveryByDate/{date}")
	@ResponseBody
	public List<Delivery> findAllDeliveryByDate(@PathVariable("date")Date date) {
		return deliveryService.findAllDeliveryByDate(date);
	}

//	http://localhost:8081/SpringMVC/servlet/findAllDeliveryByDeparture/departure
	@Override
	@GetMapping("/findAllDeliveryByDeparture/{departure}")
	@ResponseBody
	public List<Delivery> findAllDeliveryByDeparture(@PathVariable("departure")String departure) {
		return deliveryService.findAllDeliveryByDeparture(departure);
	}

//	http://localhost:8081/SpringMVC/servlet/findAllDeliveryByArrival/arrival
	@Override
	@GetMapping("/findAllDeliveryByArrival/{arrival}")
	@ResponseBody
	public List<Delivery> findAllDeliveryByArrival(@PathVariable("arrival")String arrival) {
		return deliveryService.findAllDeliveryByArrival(arrival);
	}
	
//	http://localhost:8081/SpringMVC/servlet/findAllDeliveryByDriver/id
	@Override
	@GetMapping("/findAllDeliveryByDriver/{id}")
	@ResponseBody
	public List<Delivery> findAllDeliveryByDriver(@PathVariable("id")long driverId) {
		return deliveryService.findAllDeliveryByDriver(driverId);
	}
	
	

	@Override
	@GetMapping("/findAllDelivery")
	@ResponseBody
	public List<Delivery> findAllDelivery() {
		return deliveryService.findAllDelivery();
	}

	//A voir (Cascade)
	@Override
    @DeleteMapping("/deleteAllDelivery") 
	@ResponseBody
	public void deleteAllDelivery() {
		deliveryService.deleteAllDelivery();
		
	}
	
// http://localhost:8081/SpringMVC/servlet/createDelivery/factureId/driverId
//		{"departure": "Oussema","arrival": "Saadouli","date": "23181323"}
	@Override
	@PostMapping("/createDelivery/{factureId}/{driverId}")
	@ResponseBody
	public void createDelivery(@PathVariable("factureId")long factureId,@PathVariable("driverId") long driverId,@RequestBody Delivery delivery) {
		deliveryService.createDelivery(factureId, driverId, delivery);
	}

//	http://localhost:8081/SpringMVC/servlet/deleteDelivery/1
	@Override
	@DeleteMapping("/deleteDelivery/{deliveryId}")
	@ResponseBody
	public void removeDriverFromDelivery(@PathVariable("driverId") long driverId ,@PathVariable("deliveryId") long deliveryId) {
		deliveryService.removeDriverFromDelivery(driverId, deliveryId);
	}
}
