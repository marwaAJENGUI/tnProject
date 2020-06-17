package tn.esprit.spring.service.interfaces;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entity.Ad;
import tn.esprit.spring.entity.ProductCategory;
import tn.esprit.spring.entity.User;



public interface IAdService {

	List<Ad> findAll();

	Optional<Ad> getAdById(Long id);

	Ad updateAdById(Ad ad, Long id);

	boolean removeAd(Long id);

	Ad saveAd(Ad ad);

	List<Ad> userProductAds(User user);

	List<Ad> findAllByViews();

	List<Ad> findByProductCategory(ProductCategory category);

}
