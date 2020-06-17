package tn.esprit.spring.sevice.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.ProductCategory;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.UserProductCategoryViews;
import tn.esprit.spring.repository.UserProductCategoryViewsRepository;
import tn.esprit.spring.service.interfaces.IUserProductCategoryViewsService;


@Service
public class UserProductCategoryViewsServiceImpl implements IUserProductCategoryViewsService {
	@Autowired
	UserProductCategoryViewsRepository userProductCategoryViewsRepository;
	@Override
	public Optional <UserProductCategoryViews >getUserViews(User user,ProductCategory category){
		return userProductCategoryViewsRepository.getUserViews(user, category);
	}
	@Override
	public 	UserProductCategoryViews setUserViews(UserProductCategoryViews userProductCategoryViews){
		userProductCategoryViews.setViews();
		return  userProductCategoryViewsRepository.saveAndFlush(userProductCategoryViews);
		}
}
