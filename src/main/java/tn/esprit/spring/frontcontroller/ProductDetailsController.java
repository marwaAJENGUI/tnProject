package tn.esprit.spring.frontcontroller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpServletRequest;

import org.clapper.util.misc.MIMETypeUtil;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.ProductCategory;
import tn.esprit.spring.service.interfaces.IDBFileStorageService;
import tn.esprit.spring.service.interfaces.IProductCategoryService;
import tn.esprit.spring.service.interfaces.IProductService;



@Scope(value = "session")
@Controller(value = "productDetailsController")
@ELBeanName(value = "productDetailsController")
@Join(path = "/product", to = "/product.jsf")
public class ProductDetailsController {
	
	@Autowired
	IProductService iProductService;
	@Autowired
	IProductCategoryService iProductCategoryService;
	@Autowired
	IDBFileStorageService iDBFileStorageService;
	Product product=new Product();
	ProductCategory category=new ProductCategory();
	Long barCode;
	Long categoryId;
	StreamedContent productImage;
	/*
	@RequestAction
	void init() {
		String param=
				( (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
				.getParameter("barCode");
		System.out.println("param:"+param);
		this.barCode = Long.parseLong(param);
		this.product=iProductService.getProductBybarCode(this.barCode);
	}
	*/
	
	@Transactional
	public void echoProduct() {
		this.product=iProductService.getProductBybarCode(barCode);
		this.category=iProductCategoryService.findById(categoryId).get();
		product.setCategory(category);		
	}
	
	public StreamedContent getProductImage() throws IOException, SQLException {
		return iDBFileStorageService.getStreamedFile(barCode) ;
	}
	
	public IProductService getiProductService() {
		return iProductService;
	}
	public void setiProductService(IProductService iProductService) {
		this.iProductService = iProductService;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public Long getBarCode() {
		return barCode;
	}
	public void setBarCode(Long barCode) {
		this.barCode = barCode;
	}
	public IProductCategoryService getiProductCategoryService() {
		return iProductCategoryService;
	}
	public void setiProductCategoryService(IProductCategoryService iProductCategoryService) {
		this.iProductCategoryService = iProductCategoryService;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long idCategory) {
		this.categoryId = idCategory;
	}
	public ProductDetailsController() {
		super();
		// TODO Auto-generated constructor stub
	}


}
