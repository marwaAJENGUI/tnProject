package tn.esprit.spring.service.interfaces;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entity.AdCategory;


public interface IAdCategoryService {

	List<AdCategory> findAll();

	AdCategory addAdCategory(AdCategory adCategory);

	Optional<AdCategory> getAdCategoryById(Long id);

	boolean removeAdCategory(Long id);

	AdCategory updateAdCategoryById(AdCategory adCategory, Long id);

	boolean existsById(Long id);

	AdCategory getOne(Long id);

	List<AdCategory> findCategoryByName(String name);

}
