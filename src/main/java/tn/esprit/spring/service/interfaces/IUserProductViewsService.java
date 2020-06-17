package tn.esprit.spring.service.interfaces;

import java.util.Optional;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.UserProductViews;


public interface IUserProductViewsService {

	public Optional<UserProductViews> getUserViews(User user,Product product);

	public UserProductViews setUserViews(UserProductViews userProductViews);

}
