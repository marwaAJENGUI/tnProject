package tn.esprit.spring.service.interfaces;

import java.util.Optional;

import tn.esprit.spring.entity.ProductCategory;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.UserProductCategoryViews;


public interface IUserProductCategoryViewsService {

	public Optional<UserProductCategoryViews> getUserViews(User user, ProductCategory category);

	public UserProductCategoryViews setUserViews(UserProductCategoryViews userProductCategoryViews);

}
