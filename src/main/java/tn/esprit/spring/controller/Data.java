package tn.esprit.spring.controller;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.EventCategory;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.VehiculeType;




@Scope(value = "session")
@Controller(value = "data")
@ELBeanName(value = "data")
public class Data {

	public Role[] getRoles() {
		return Role.values();
	}
	
	public VehiculeType[] getTypes() {
		return VehiculeType.values();
	}
	public EventCategory[] getEventCategories(){
		return EventCategory.values();
	}
}
