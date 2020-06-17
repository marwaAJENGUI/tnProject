package tn.esprit.spring.sevice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.ProductCategoryRepository;
import tn.esprit.spring.service.interfaces.IProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
	@Autowired
	ProductCategoryRepository 	productCategoryRepository;
}
