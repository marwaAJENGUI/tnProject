package tn.esprit.spring.frontcontroller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Delivery;
import tn.esprit.spring.entity.Status;
import tn.esprit.spring.service.interfaces.IDeliveryService;




@Scope(value = "session")
@Controller(value = "deliveryFController")
@ELBeanName(value = "deliveryFController")
public class DeliveryController {

	
	@Autowired
	IDeliveryService iDeliveryService;
	
	
	
	private String departure;
	private String arrival;
	private Date date ;
	
	private Status status;
	
	
	private Long deliveryIdToBeUpdated;
//	private long selectDriverId;
	public static long selectDriverId;
	private long selectFactureId;
	private long selectDeliveryId;
	
	
	private List<Delivery> deliverys;
	private List<Delivery> waitingDeliverys;
	private List<Delivery> beingDoneDeliverys;
	private List<Delivery> doneDeliverys;
	
	public DeliveryController() {
		super();
	}

	
	public void clear() {
		setDeparture(null);
		setArrival(null);
	}

	public String back() {
		clear();
		return "/admin/adminDash.jsf?faces-redirect=true";
	}
	
	public String driverLocation() {
		clear();
		return "/map.jsf?faces-redirect=true";
	}
	
	public void onDateSelect(SelectEvent event) {  
		FacesContext facesContext = FacesContext.getCurrentInstance();  
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));  
		}  
		public void click() {  
		PrimeFaces requestContext = PrimeFaces.current();  
		requestContext.ajax().update("form:display");  
		requestContext.executeScript("PF('dlg').show()");  
		}  
		
		
		public void createDelivery() {
			iDeliveryService.createDelivery(selectFactureId,selectDriverId,new Delivery(departure,arrival,date));
			clear();
	}
		public void deleteDeliveryById(long deliveryId) {
			iDeliveryService.deleteDeliveryById(deliveryId);
	}
		
		public String removeDriverFromDelivery() {
			String navigateTo="null";
			iDeliveryService.removeDriverFromDelivery(selectDriverId, selectDeliveryId);
			clear();
			navigateTo = "/admin/Delivery/createDelivery.jsf?faces-redirect=true";
			return navigateTo;	
	}
		
	
		public String copyDeliveryInfo(Delivery delivery) {
			this.setDeliveryIdToBeUpdated(delivery.getId());
			this.setDeparture(delivery.getDeparture());
			this.setArrival(delivery.getArrival());
			this.setStatus(delivery.getStatus());
			return "/admin/Delivery/updateDelivery.jsf?faces-redirect=true";
		}


	public String getDeparture() {
		return departure;
	}


	public void setDeparture(String departure) {
		this.departure = departure;
	}


	public String getArrival() {
		return arrival;
	}


	public void setArrival(String arrival) {
		this.arrival = arrival;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public long getSelectDriverId() {
		return selectDriverId;
	}


	public void setSelectDriverId(long selectDriverId) {
		this.selectDriverId = selectDriverId;
	}


	public List<Delivery> getDeliverys() {
		deliverys = iDeliveryService.findAllDelivery();
		return deliverys;
	}
	
	public List<Delivery> getWaitingDeliverys() {
		waitingDeliverys = iDeliveryService.findWaitingDeliverys(UserController.authenticatedDriver.getId());
		return waitingDeliverys;
	}
	
	public List<Delivery> getBeingDoneDeliverys() {
		beingDoneDeliverys = iDeliveryService.findBeingDoneDeliverys(UserController.authenticatedDriver.getId());
		return beingDoneDeliverys;
	}
	
	public List<Delivery> getDoneDeliverys() {
		doneDeliverys = iDeliveryService.findDoneDeliverys(UserController.authenticatedDriver.getId());
		return doneDeliverys;
	}


	public long getSelectDeliveryId() {
		return selectDeliveryId;
	}


	public void setSelectDeliveryId(long selectDeliveryId) {
		this.selectDeliveryId = selectDeliveryId;
	}



	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public Long getDeliveryIdToBeUpdated() {
		return deliveryIdToBeUpdated;
	}


	public void setDeliveryIdToBeUpdated(Long deliveryIdToBeUpdated) {
		this.deliveryIdToBeUpdated = deliveryIdToBeUpdated;
	}


	public long getSelectFactureId() {
		return selectFactureId;
	}


	public void setSelectFactureId(long selectFactureId) {
		this.selectFactureId = selectFactureId;
	}


	public void setDeliverys(List<Delivery> deliverys) {
		this.deliverys = deliverys;
	}


	public void setWaitingDeliverys(List<Delivery> waitingDeliverys) {
		this.waitingDeliverys = waitingDeliverys;
	}


	public void setBeingDoneDeliverys(List<Delivery> beingDoneDeliverys) {
		this.beingDoneDeliverys = beingDoneDeliverys;
	}


	public void setDoneDeliverys(List<Delivery> doneDeliverys) {
		this.doneDeliverys = doneDeliverys;
	}

}
