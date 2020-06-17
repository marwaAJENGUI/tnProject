package tn.esprit.spring.frontcontroller;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.clapper.util.misc.MIMETypeUtil;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.service.interfaces.IDBFileStorageService;
import tn.esprit.spring.service.interfaces.IProductCategoryService;
import tn.esprit.spring.service.interfaces.IProductService;



@Scope(value = "session")
@Controller(value = "productJSFController")
@ELBeanName(value = "productJSFController")
@Join(path = "/products", to = "/products.jsf")
//@ManagedBean(name="productJSFController")
public class ProductJSFController  {
		
	@Autowired
	IProductService iProductService;
	@Autowired
	IProductCategoryService iProductCategoryService;
	Product product=new Product();
	List<Product> products=new ArrayList<Product>();
	Long categoryId;
	String productName;
	String categoryName;
	String redirectTo;
	
	@Autowired
	IDBFileStorageService iDBFileStorageService;
	UploadedFile file;
	Boolean disabledBarCode=true;

	
	@PostConstruct
	void initProducts() {
		this.products=iProductService.findAll();
	}
		
	public String  addProduct() {
		product.setCategory(iProductCategoryService.findById(categoryId).get());
		iProductService.addProduct(this.product);
		redirectTo="/product?faces-redirect=true&amp;barCode="+product.getBarCode()+"&amp;categoryId="+categoryId;
		this.products=iProductService.findAll();
		this.product=new Product();
		categoryId=null;
		return redirectTo;
	}
	
	
	@Transactional
	public StreamedContent getProductImage() throws IOException, SQLException {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	        // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
			return new DefaultStreamedContent();
		}
		else {
	        // So, browser is requesting the image. Get ID value from actual request param.
			String imageName=context.getExternalContext().getRequestParameterMap().get("pid");
			Long id =Long.parseLong( imageName);
			return iDBFileStorageService.getStreamedFile(id) ;
		}
	}
	
	public List<Product> getProducts() {		
		return this.products;		
	}
	
	public String removeProduct(Product prod) {
		iProductService.removeProduct(prod.getBarCode());
		this.products.remove(prod);
		this.products=iProductService.findAll();
		return "products?faces-redirect=true";

	}
	
	
	public String editProduct (Product updateProduct){
		this.setProduct(updateProduct);
		this.setCategoryId(updateProduct.getCategory().getId());
		return "products?faces-redirect=true";
	}
	
	@Transactional
	public String updateProduct(){
		iProductService.updateProduct(this.product, this.product.getBarCode());
		redirectTo="product?faces-redirect=true&amp;barCode="+product.getBarCode()+"&amp;categoryId="+categoryId;
		this.product=new Product();
		this.categoryId=null;
		this.products=iProductService.findAll();
		return redirectTo;
	}
	
	public String echoProduct() {
		//return "product?faces-redirect=true";
		return "product";// =>product.xhtml
	}
	public String echoCategory() {
		return "productCategory";// =>productCategory.xhtml
	}
	
	public String searchProducts(){	
		this.products= iProductService.searchProducts(productName, categoryName);
		if (this.products.isEmpty()) {
			this.products=iProductService.findAll();
		}
		return "products?faces-redirect=true";
	}
	
	public void upload() {
		if (file != null)  {
			iDBFileStorageService.storeLocalFile(file);
        	String strBarCode=iDBFileStorageService.getBarCode(file.getFileName());
        	String str="invalid file";
        	if (strBarCode.equals(str)) {
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " BarCode Not Found", " BarCode Not Found in the uploaded file");
                FacesContext.getCurrentInstance().addMessage(null, message);
        	}else {
        	product.setBarCode(Long.parseLong(strBarCode));
        	this .disabledBarCode=false;
        	FacesMessage message = new FacesMessage("Successful", file.getFileName()+ " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        	}
		}
    } 
    
    
    public void editDisabledBarCode() {
    	this.disabledBarCode=false;
    }
	public UploadedFile getFile() {
       return file;
   }
   
	
	public String getRedirectTo() {
		return redirectTo;
	}

	public void setRedirectTo(String redirectTo) {
		this.redirectTo = redirectTo;
	}

	public Boolean getDisabledBarCode() {
		return disabledBarCode;
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

	public void setProducts(List<Product> products) {
		this.products = products;
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

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
 
    public IDBFileStorageService getiDBFileStorageService() {
		return iDBFileStorageService;
	}

	public void setiDBFileStorageService(IDBFileStorageService iDBFileStorageService) {
		this.iDBFileStorageService = iDBFileStorageService;
	}

	public void setDisabledBarCode(Boolean disabledBarCode) {
		this.disabledBarCode = disabledBarCode;
	}



	public ProductJSFController() {
		super();
	}
	
}
