package tn.esprit.spring.frontcontroller;


import java.io.IOException;
import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import tn.esprit.spring.entity.Driver;
import tn.esprit.spring.entity.Location;
import tn.esprit.spring.service.interfaces.IDriverService;



@Scope(value = "session")
@Controller(value = "driverFController")
@ELBeanName(value = "driverFController")
public class DriverController {

	
	@Autowired
	IDriverService iDriverService;
	
	
	private static boolean loggedin =false;
	private String firstName;
	private String lastName;
	private String password;
	private String phoneNumber;
	
	private Long driverIdToBeUpdated;
	private int taskCompleted;
	
	private float salary;
	private float prime;
	
	private boolean available;
	
	private String ip;
	

	private List<Driver> drivers;
	
	private float latitude;
	private float longitude;

	public void clear(){
		setFirstName(null);
		setLastName(null);
		setPhoneNumber(null);
		setIp(null);
		setSalary(0);
	}
	
	
	public String back() {
		 clear();
		 return "/admin/Driver/allDrivers.jsf?faces-redirect=true";
	}
	
	public String backToDelivery() {
		 clear();
		 return "/admin/Delivery/createDelivery.jsf?faces-redirect=true";
	}
	
	
	
	public String addDriver() {
			String navigateTo="null";
			iDriverService.addDriver(new Driver(firstName,lastName,password,phoneNumber,salary));
			clear();
			navigateTo = "/admin/Driver/allDrivers.jsf?faces-redirect=true";
			return navigateTo;
	}
	
	public String deleteDriverByID(int driverId) {
		String navigateTo="null";
		iDriverService.deleteDriverByID(driverId);
		navigateTo = "/allDrivers.jsf?faces-redirect=true";
		return navigateTo;
	}

	
	
	public String updateDriver() {
		Driver d = new Driver(driverIdToBeUpdated,firstName,lastName,password,phoneNumber,salary,prime,taskCompleted,available,ip);
		iDriverService.updateDriver(d);
		clear();
		return "/admin/Driver/allDrivers.jsf?faces-redirect=true";
		 
	}
	
	
	public String copyDriverInfo(Driver driver) {
		
		this.setDriverIdToBeUpdated(driver.getId());
		this.setFirstName(driver.getFirstName());
		this.setLastName(driver.getLastName());
		this.setPassword(driver.getPassword());
		this.setPhoneNumber(driver.getPhoneNumber());
		this.setSalary(driver.getSalary());
		this.setPrime(driver.getPrime());
		this.setTaskCompleted(driver.getTaskCompleted());
		this.setAvailable(driver.isAvailable());
		this.setIp(driver.getIp());
		 return "/admin/Driver/updateDriver.jsf?faces-redirect=true";
	}
	
	
	public String acceptDelivery(long deliveryId) {
		iDriverService.acceptDelivery(deliveryId);
		return "/driver/bDelivery.jsf?faces-redirect=true"; 
	}
	
	public String markDeliveryAsFinished(long deliveryId) {
		iDriverService.markDeliveryAsFinished(deliveryId);
		return "/driver/history.jsf?faces-redirect=true"; 
	}
	
	
	public String findDriverLocation() throws IOException, GeoIp2Exception {
		Location loc = iDriverService.findDriverLocation(DeliveryController.selectDriverId);
		latitude =loc.getLatitude() ;
		longitude =loc.getLongitude() ;
		return "/map.jsf?faces-redirect=true"; 
	}
		
	
	
	public String getFirstName() {
		return firstName;
	}
	public float getLatitude() {
		return latitude;
	}


	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}


	public float getLongitude() {
		return longitude;
	}


	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public float getSalary() {
		return salary;
	}



	public void setSalary(float salary) {
		this.salary = salary;
	}

	
	


	public Long getDriverIdToBeUpdated() {
		return driverIdToBeUpdated;
	}


	public void setDriverIdToBeUpdated(Long driverIdToBeUpdated) {
		this.driverIdToBeUpdated = driverIdToBeUpdated;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public List<Driver> getDrivers() {
		drivers = iDriverService.findAllDrivers();
		return drivers;
	}


	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}


	public int getTaskCompleted() {
		return taskCompleted;
	}


	public void setTaskCompleted(int taskCompleted) {
		this.taskCompleted = taskCompleted;
	}


	public float getPrime() {
		return prime;
	}


	public void setPrime(float prime) {
		this.prime = prime;
	}


	public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public static boolean isLoggedin() {
		return loggedin;
	}


	public static void setLoggedin(boolean loggedin) {
		DriverController.loggedin = loggedin;
	}
	
}
