package tn.esprit.spring.frontcontroller;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.interfaces.IUserService;


@Scope(value = "session")
@Controller(value = "signUpController")
@ELBeanName(value = "signUpController")
@Join(path = "/signUp", to = "/signUp.jsf")
public class SignUpController {
	@Autowired
	IUserService iUserService;
	User user;
	String confirmPassword;
	
	@PostConstruct
	void init() {
		user =new User();
		confirmPassword="";
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
	
	public void setUser (User user) {
		this.user=user;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String register() {
		System.out.println("user="+user.toString());
		this.user.setRole(Role.USER);
		if ((this.user.getPassword().equals(confirmPassword)) && !this.confirmPassword.isEmpty()){
			System.out.println("user="+user.toString());
			this.user = this.iUserService.addUser(user);
			this.user=new User();
		}
		return "thanks.jsf?faces-redirect=true";
	}
	
	public void validatePassword(ComponentSystemEvent event) {

		  FacesContext fc = FacesContext.getCurrentInstance();

		  UIComponent components = event.getComponent();

		  // get password
		  UIInput uiInputPassword = (UIInput) components.findComponent("password");
		  String password = uiInputPassword.getLocalValue() == null ? ""
			: uiInputPassword.getLocalValue().toString();
		  String passwordId = uiInputPassword.getClientId();

		  // get confirm password
		  UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");
		  String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
			: uiInputConfirmPassword.getLocalValue().toString();

		  // Let required="true" do its job.
		  if (password.isEmpty() || confirmPassword.isEmpty()) {
			return;
		  }

		  if (!password.equals(confirmPassword)) {

			FacesMessage msg = new FacesMessage("Password must match confirm password");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(passwordId, msg);
			fc.renderResponse();

		  }
	}

	public SignUpController() {
		super();
		// TODO Auto-generated constructor stub
	}

}
