package tn.esprit.spring.sevice.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Ad;
import tn.esprit.spring.entity.AdCategory;
import tn.esprit.spring.repository.AdCategoryRepository;
import tn.esprit.spring.repository.AdRepository;
import tn.esprit.spring.service.interfaces.IAdCategoryService;




@Service
public class AdCategoryServiceImpl implements IAdCategoryService{
	@Autowired
	AdCategoryRepository adCategoryRepository;
	@Autowired
	AdRepository adRepository;

	@Override
	public List<AdCategory> findAll() {
		return adCategoryRepository.findAll();

	}

	@Override
	public AdCategory addAdCategory(AdCategory adCategory) {
		return adCategoryRepository.save(adCategory);			
	}

	@Override
	public Optional<AdCategory> getAdCategoryById(Long id) {
		return adCategoryRepository.findById(id);
	}

	@Override
	public boolean removeAdCategory(Long id) {
		AdCategory category=new AdCategory();
		if (adCategoryRepository.existsById(id)) {
			if(adCategoryRepository.findById(id).get().getName().equals("No one")) return false;
			category=adCategoryRepository.getOne(id);
			if(category.getAds().size()>0) {
			List <AdCategory> list=adCategoryRepository.findCategoryByName("No one");
			if(list.size()==0) {
				category.setName("No one");
				category=adCategoryRepository.saveAndFlush(category);
			} else {
				category=list.get(0); 
				List <Ad> ads=adRepository.findAdsByCategory(id);
					for (Ad ad:ads) {
						ad.setCategory(category);
						adRepository.save(ad);
					}
			}
			}
			adCategoryRepository.delete(adCategoryRepository.getOne(id));
		}
		return true;
	}

	@Override
	public AdCategory updateAdCategoryById(AdCategory adCategory, Long id) {
		AdCategory category = adCategoryRepository.getOne(id);
		adCategoryRepository.updateProductCategoryById(adCategory.getName(),id);
		return category;	
	}
	@Override
	public boolean existsById(Long id){
		return adCategoryRepository.existsById(id);
	}
	@Override
	public AdCategory getOne(Long id) {
		return adCategoryRepository.getOne(id);
	}
	@Override
	public List< AdCategory> findCategoryByName(String name){
		return adCategoryRepository.findCategoryByName(name);
	}

	
	
}
