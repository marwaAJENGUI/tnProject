package tn.esprit.spring.frontcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import tn.esprit.spring.entity.ProductCategory;
import tn.esprit.spring.service.interfaces.IProductCategoryService;



@Scope(value = "session")
@Controller(value = "productCategoryJSFController")
@ELBeanName(value = "productCategoryJSFController")
@Join(path = "/productsCategories", to = "/productsCategories.jsf")
//@ManagedBean//(eager=true)
//@ViewScoped
//@RequestScoped
public class ProductCategoryJSFController {
	@Autowired
	//@Qualifier("prodCatService")
	//@ManagedProperty(value = "#{prodCatService}")
	IProductCategoryService iProductCategoryService ;
	ProductCategory category=new ProductCategory();
	List<ProductCategory> categories=new ArrayList<ProductCategory>() ;

	
	@PostConstruct
	public List<ProductCategory> initCategories() {
		this.categories=iProductCategoryService.findAll();
		 return this.categories;
	}	
	
	public List<ProductCategory> getCategories() {
		return categories;
	}

	public String addProductCategory() {		
		iProductCategoryService.addProductCategory(category);
		this.category=new ProductCategory();
		this.categories=iProductCategoryService.findAll();
		return "productsCategories?faces-redirect=true";

	}
	public String echoCategory() {
		return "productCategory?faces-redirect=true";
	}

	public String removeProductCategory(ProductCategory cat){
		iProductCategoryService.removeProductCategory(cat.getId());
		this.categories.remove(cat);
		this.categories=iProductCategoryService.findAll();
		return "productsCategories?faces-redirect=true";
	}

	public String editProductCategory (ProductCategory updateCategory){
		this.setCategory(updateCategory);
		return "productsCategories?faces-redirect=true";
	}
	public String updateProductCategory(){
		iProductCategoryService.updateProductCategoryById(category, category.getId());
		this.category=new ProductCategory();
		this.categories=iProductCategoryService.findAll();
		return "productsCategories?faces-redirect=true";
	}
	
	public String echoCategory(ProductCategory category) {
		this.category=category;
		return "/productsCategories?faces-redirect=true";
	}
	
	public IProductCategoryService getiProductCategoryService() {
		return iProductCategoryService;
	}
	public void setCategories(List<ProductCategory> categories) {
		this.categories = categories;
	}
	public void setiProductCategoryService(IProductCategoryService iProductCategoryService) {
		this.iProductCategoryService = iProductCategoryService;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public void setCategories() {
		this.categories = iProductCategoryService.findAll();
	}	
	public ProductCategoryJSFController() {
		super();
	}
	
}
