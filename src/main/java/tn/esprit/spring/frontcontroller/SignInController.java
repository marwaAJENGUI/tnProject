package tn.esprit.spring.frontcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.interfaces.IDBFileStorageService;
import tn.esprit.spring.service.interfaces.IUserService;

@Scope(value = "session")
@Controller(value = "signInController")
@ELBeanName(value = "signInController")
@Join(path = "/signIn", to = "/signIn.jsf")
public class SignInController {
	@Autowired
	IUserService iUserService;
	User user=new User();
	String username= new String();
	String password= new String();
	@Autowired
	IDBFileStorageService iDBFileStorageService;
	StreamedContent image;
	Boolean loggedIn=false;
	String template;
	Boolean isAdmin;
	
	@PostConstruct
	void init() {
		user = new User();
		username = new String();
		password = new String();
		loggedIn=false;
	}
	

	public String login() {

		String navigateTo = "null";
		user = iUserService.findUserByUsername(username);
		System.out.println(user.toString());
		if (user.equals(new User())) {
			FacesMessage facesMessage = new FacesMessage("Login Failed: please check your username and try again.");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		} else if (!user.getPassword().equals(password)) {
			FacesMessage facesMessage = new FacesMessage("Login Failed: please check your password and try again.");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		} else {
			loggedIn=true;
			if (this.user.getRole().equals(Role.ADMINISTRATEUR)) {
				this.template="template";
				this.isAdmin=true;
			}
			else{
				this.template="template";
				this.isAdmin=false;
			}
			navigateTo = "profile.jsf?faces-redirect=true";
		}
		return navigateTo;
	}

	public String logout() {
		loggedIn=false;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "welcom.jsf?faces-redirect=true";
	}
	
	public StreamedContent getImage() throws IOException, SQLException {
		System.out.println("user id="+this.user.getId());
		System.out.println("user name="+this.username);
		image=iDBFileStorageService.getStreamedFile(user.getId()) ;
		return image;
	}
	
	

	public Boolean getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public String getTemplate() {
		return template;
	}


	public void setTemplate(String template) {
		this.template = template;
	}


	public IUserService getiUserService() {
		return iUserService;
	}

	public void setiUserService(IUserService iUserService) {
		this.iUserService = iUserService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public SignInController() {
		super();
		// TODO Auto-generated constructor stub
	}

}
