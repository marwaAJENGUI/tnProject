package tn.esprit.spring.frontcontroller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Claim;
import tn.esprit.spring.service.interfaces.IClaimService;


@Scope(value = "session")
@Controller(value = "claimFController")
@ELBeanName(value = "claimFController")
public class ClaimController {
	
	
	@Autowired
	IClaimService iClaimService;
	

	private String name;
	private String subject;
	private String email;
	private String phone;
	private String Message;
	
	private List<Claim> claims;
	
	public ClaimController() {}

	
	public void clear(){
		setName(null);
		setSubject(null);
		setEmail(null);
		setPhone(null);
		setMessage(null);
	}

	public void addClaim() {
//		String navigateTo="null";
		iClaimService.addClaim(new Claim(name,subject,email,phone,Message));
		clear();
//		navigateTo = "/welcome.jsf?faces-redirect=true";
//		return navigateTo;
		FacesMessage facesMessage = new FacesMessage("Thank you , You Will receive a respone soon !");
		FacesContext.getCurrentInstance().addMessage("form:mybutton", facesMessage);
}
	
	public String deleteClaimByID(long claimId) {
		String navigateTo="null";
		iClaimService.deleteClaimByID(claimId);
		clear();
		navigateTo = "/admin/Claim/allClaims.jsf?faces-redirect=true";
		return navigateTo;
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


	public List<Claim> getClaims() {
		claims = iClaimService.findAllClaims();
		return claims;
	}


	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}
	
}
