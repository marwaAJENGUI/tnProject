package tn.esprit.spring.frontcontroller;

import java.util.ArrayList;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.ProductCategory;
import tn.esprit.spring.service.interfaces.IProductCategoryService;
import tn.esprit.spring.service.interfaces.IProductService;


@Scope(value = "request")
@Controller(value = "productCategoryDetailsController")
@ELBeanName(value = "productCategoryDetailsController")
@Join(path = "/category", to = "/productCategory.jsf")
public class ProductCategoryDetailsController {
	@Autowired
	IProductService iProductService;
	@Autowired
	IProductCategoryService iProductCategoryService;
	List<Product> products=new ArrayList<Product>();
	ProductCategory category=new ProductCategory();
	Long categoryId;
	

	public void echoCategory() {
		this.category=iProductCategoryService.findById(categoryId).get();
		this.products=iProductService.findByCategory(category);
	}

	public IProductService getiProductService() {
		return iProductService;
	}

	public void setiProductService(IProductService iProductService) {
		this.iProductService = iProductService;
	}

	public IProductCategoryService getiProductCategoryService() {
		return iProductCategoryService;
	}

	public void setiProductCategoryService(IProductCategoryService iProductCategoryService) {
		this.iProductCategoryService = iProductCategoryService;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public ProductCategoryDetailsController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
