package tn.esprit.spring.sevice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.DBFile;
import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.ProductCategory;
import tn.esprit.spring.repository.DBFileRepository;
import tn.esprit.spring.repository.ProductCategoryRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.service.interfaces.IProductService;




@Service
public class ProductServiceImpl implements IProductService{
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductCategoryRepository productCategoryRepository;
	@Autowired
	DBFileRepository dbFileRepository;
	@Override
	public Product addProduct(Product product) {
		productRepository.save(product);
		return product;
	}

	@Override
		public Product updateProduct( Product product,Long barCode) {
			Product p=product;
			p=productRepository.save(product);
			return 	p;
	}
	
	@Override
	public List<Product> findByCategory(ProductCategory category){
		return productRepository.findByCategory(category);
	}
	
	@Override
	public List<Product> findProductsByCategory(Long idCategory) {
		return productRepository.findProductsByCategory(idCategory);
	}
	/*
	 * ProductCategory validCategory(ProductCategory category) {
		if (category==null) throw new IllegalArgumentException("Ad Category can not be empty");
		if((category.getId()!=null)&&(productCategoryRepository.existsById(category.getId()))) {
			return productCategoryRepository.getOne(category.getId());
		}else if ((category.getName()!=null)&&(productCategoryRepository.findCategoryByName(category.getName()).size()>0)) {
			return productCategoryRepository.findCategoryByName(category.getName()).get(0);			
		}else throw new IllegalArgumentException("Invalid Ad Category, could not find reference");
	}
	 */

	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductBybarCode(Long barCode) {
		return productRepository.getOne(barCode);
	}
	
	@Override
	public boolean removeProduct(Long barCode) {
		if (productRepository.existsById(barCode)) {
			productRepository.delete(productRepository.getOne(barCode));
			Optional <DBFile> file=dbFileRepository.findById(barCode);
			if ( file.isPresent()) {
				dbFileRepository.delete(file.get());
			}
			return true;
		}
		else {
			throw new IllegalArgumentException("Invalid Bar Code, Product do not exist");
		}	
	}
	@Override
	public List<Product> searchProducts(String productName,String categoryName){		
		List<Product> 	products=productRepository.findAll();
		boolean containsProd=true;
		boolean containsCat=true;
		List<Product> 	result=  new ArrayList<>();
		if ((productName==null)||(productName.equals(""))) containsProd=false;
		if ((categoryName==null)||(categoryName.equals(""))) containsCat=false;
		if (!containsProd && !containsCat) return result;
		int nbProducts=products.size();	
		Product product=new Product();	
		if (containsProd &&containsCat){
			for(int index = 0; index < nbProducts; index++){
				product=products.get(index);
				containsProd=true;
				containsCat=true;
				if ((product.getName()==null)||(product.getName().equals(""))) {containsProd=false;}
				else {containsProd=product.getName().contains(productName);}
				if((product.getCategory().getName()==null)||(product.getCategory().getName().equals(""))) {containsCat=false;}
				else {containsCat=product.getCategory().getName().contains(categoryName);}
				if ( containsProd && containsCat){
					result.add(product);
				}
			}			
		}
		
		else if (!containsCat) {
			for(int index = 0; index < nbProducts; index++){
				product=products.get(index);
				containsProd=true;
				if((product.getName()==null)||(product.getName().equals(""))) {containsProd=false;}
				else {containsProd=product.getName().contains(productName);}
				if ( containsProd){
					result.add(product);
				}
			}
		}
		
		else {
			for(int index = 0; index < nbProducts; index++){
				product=products.get(index);
				containsCat=true;
				if ((product.getCategory().getName()==null)||(product.getCategory().getName().equals(""))) {containsCat=false;}
				else {containsCat=product.getCategory().getName().contains(categoryName);}
				if (containsCat){
					result.add(product);
				}
			}			
		}		
		return result;		
	}
	@Override
	public boolean existsById(Long id){
		return productRepository.existsById(id);
	}
	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}
	@Override
	public List< Product> findProductByName(String name){
		return productRepository.findProductByName(name);
	}


}


