package tn.esprit.spring.sevice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.UserProductCategoryViewsRepository;
import tn.esprit.spring.service.interfaces.IUserProductCategoryViewsService;

@Service
public class UserProductCategoryViewsServiceImpl implements IUserProductCategoryViewsService {
	@Autowired
	UserProductCategoryViewsRepository userProductCategoryViewsRepository;
}
