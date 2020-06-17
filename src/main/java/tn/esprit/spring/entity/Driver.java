package tn.esprit.spring.entity;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name = "driver")
public class Driver implements Serializable{

	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int taskCompleted=0;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String phoneNumber;

	private boolean available = true;
	
	private float salary;
	
	private float prime=0;
	@Enumerated(EnumType.STRING)
	private Role role = Role.DRIVER;
	
	private String ip;
	
	

	@OneToMany(mappedBy="driver")
	private List<Delivery> deliveryList ;
	
	

//	@OneToMany(mappedBy="driver",fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@OneToMany(mappedBy="driver")
	private List<Vehicule> vehiculeList;
	

	public Driver() {}
	
	
	




	public Driver(String firstName, String lastName, String password,String phoneNumber, float salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.salary = salary;
	}
	
	

	public Driver(Long driverIdToBeUpdated, String firstName, String lastName,String password, String phoneNumber, float salary,
			float prime, int taskCompleted,boolean available,String ip) {
		super();
		this.id = driverIdToBeUpdated;
		this.taskCompleted = taskCompleted;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.available = available;
		this.ip = ip;
		this.salary = salary;
		this.prime = prime;
	}







	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Delivery> getDeliveryList() {
		return deliveryList;
	}

	public void setDeliveryList(List<Delivery> deliveryList) {
		this.deliveryList = deliveryList;
	}


	public List<Vehicule> getVehiculeList() {
		return vehiculeList;
	}

	public void setVehiculeList(List<Vehicule> vehiculeList) {
		this.vehiculeList = vehiculeList;
	}

	public String getFirstName() {
		return firstName;
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


	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
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
	
	

	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
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







	@Override
	public String toString() {
		return firstName+" "+lastName;
	}
}
