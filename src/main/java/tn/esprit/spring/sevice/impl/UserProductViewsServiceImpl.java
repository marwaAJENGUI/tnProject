package tn.esprit.spring.sevice.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.UserProductViews;
import tn.esprit.spring.repository.UserProductViewsRepository;
import tn.esprit.spring.service.interfaces.IUserProductViewsService;



@Service
public class UserProductViewsServiceImpl implements IUserProductViewsService {
	@Autowired
	UserProductViewsRepository userProductViewsRepository;
	@Override
	public Optional<UserProductViews> getUserViews(User user,Product product) {
				return  userProductViewsRepository.getUserViews(user, product);
	}
	@Override
	public 	UserProductViews setUserViews(UserProductViews userProductViews){
		userProductViews.setViews();
		return userProductViewsRepository.saveAndFlush(userProductViews);
		}

}
