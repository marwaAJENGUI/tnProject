package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.service.interfaces.IUserProductViewsService;

@RestController
public class UserProductViewsContorller{
	@Autowired
	IUserProductViewsService iUserProductViewsService;
}
