package tn.esprit.spring.controller;

import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.service.interfaces.IProductService;

@RestController
public class ProductController {
	IProductService iProductService;
}
