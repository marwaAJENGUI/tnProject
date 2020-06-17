package tn.esprit.spring.sevice.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Ad;
import tn.esprit.spring.entity.ProductCategory;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.AdCategoryRepository;
import tn.esprit.spring.repository.AdRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.service.interfaces.IAdService;



@Service
public class AdServiceImpl implements IAdService{
	@Autowired
	AdRepository adRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	AdCategoryRepository adCategoryRepository;

	@Override
	public List<Ad> findAll() {
		return adRepository.findAll();
	}

	@Override
	public Optional<Ad> getAdById(Long id) {
		return 		adRepository.findById(id);
	}

	@Override
	public Ad saveAd(Ad ad) {
		return adRepository.save(ad);		
	}
	
	@Override
	public Ad updateAdById(Ad ad, Long id) {
		adRepository.flush();
		return adRepository.findById(id).get(); 
	}
	
	@Override
	public boolean removeAd(Long id) {
		adRepository.delete(adRepository.getOne(id));
		return true;
	}
	@Override
	public List<Ad> userProductAds(User user){
		Date today=new Date();
		return adRepository.findUserAds(user, today);
	}
	@Override
	public List<Ad> findAllByViews(){
		Date today=new Date();
		return adRepository.findAllByViews(today);
	}
	@Override
	public List<Ad> findByProductCategory(ProductCategory category){
	Date today=new Date();
	return adRepository.findAdsByProductCategory(category,today);
	}
	

}