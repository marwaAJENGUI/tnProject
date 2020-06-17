package tn.esprit.spring.frontcontroller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Driver;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.interfaces.IDriverService;
import tn.esprit.spring.service.interfaces.IUserService;

@Scope(value = "session")
@Controller(value = "userFController")
@ELBeanName(value = "userFController")
@Join(path = "/", to = "/welcome.jsf")
public class UserController {
	

	@Autowired
	IUserService UserService;
	
	@Autowired
	IDriverService DriverService;
	

	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	private User authenticatedUser;
	
	public static Driver authenticatedDriver;
	
	public static User USERCONNECTED ;

	
	
	private String login;
	private Boolean loggedIn;
	private String password;
	
	public UserController() {
		authenticatedUser = new User();
		authenticatedDriver = new Driver();
		loggedIn = false;
		}
	
	public String back() {
		 return "/welcome.jsf?faces-redirect=true";
	}
	
	
	public String dologin()  {
		
		String navigateTo = "null";
		String Username = authenticatedUser.getUsername();
		String Userpass = authenticatedUser.getPassword();
		
		User authUser =  UserService.findUserByUsername(Username);	
		authenticatedUser.setFirstName(Username);
		
		Driver authDriver =  DriverService.findDriverByFirstName(Username);	
		if(authDriver !=null) {
		authenticatedDriver.setId(authDriver.getId());
		}
		authenticatedDriver.setPassword(Userpass);
		String Driverpass = authenticatedDriver.getPassword();

		if (authUser != null && authUser.getRole() == Role.ADMINISTRATEUR && bcryptEncoder.matches(Userpass, authUser.getPassword())) {
			USERCONNECTED = authUser;
			navigateTo = "/admin/adminDash.jsf?faces-redirect=true";
			loggedIn = true;
		}
		if (authUser != null && authUser.getRole() == Role.CLIENT && bcryptEncoder.matches(Userpass, authUser.getPassword())) {
			USERCONNECTED = authUser;
			navigateTo = "/template/template.jsf?faces-redirect=true";
			loggedIn = true;
		}
		else if (authDriver != null && authDriver.getRole() == Role.DRIVER && bcryptEncoder.matches(Driverpass, authDriver.getPassword())){
			navigateTo = "/welcomeDriver.jsf?faces-redirect=true";
//			DriverController.setLoggedin(true);
			loggedIn = true;
			
		}
		
		else if (authUser != null && authUser.getRole() == Role.USER && bcryptEncoder.matches(Userpass, authUser.getPassword())){
			navigateTo = "/admin/welcome.jsf?faces-redirect=true";
			loggedIn = true;
		}
		else {

			FacesMessage facesMessage = new FacesMessage("Login Failed: please check your username/password and try again.");
			FacesContext.getCurrentInstance().addMessage("form:mybutton", facesMessage);
		}

		return navigateTo;
	}
	
	
	public void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}


	
	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		loggedIn = false;
		return "/welcome.jsf?faces-redirect=true";
	}
	
	
	public User getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setAuthenticatedUser(User authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Driver getAuthenticatedDriver() {
		return authenticatedDriver;
	}

	@Override
	public String toString() {
		return "UserController [authenticatedUser=" + authenticatedUser + ", login=" + login + ", loggedIn=" + loggedIn
				+ ", password=" + password + "]";
	}	
}
