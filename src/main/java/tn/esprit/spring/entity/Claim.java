package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "claim")
public class Claim  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String subject;
	private String email;
	private String phone;
	private String Message;
	
	
	public Claim() {
	}


	public Claim(Long id, String name, String subject, String email, String phone, String message) {
		super();
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.email = email;
		this.phone = phone;
		Message = message;
	}
	
	


	public Claim(String name, String subject, String email, String phone, String message) {
		super();
		this.name = name;
		this.subject = subject;
		this.email = email;
		this.phone = phone;
		Message = message;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getMessage() {
		return Message;
	}


	public void setMessage(String message) {
		Message = message;
	}


	@Override
	public String toString() {
		return "Claim [id=" + id + ", name=" + name + ", subject=" + subject + ", email=" + email + ", phone=" + phone
				+ ", Message=" + Message + "]";
	}
}
