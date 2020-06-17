package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.EAN;
import org.hibernate.validator.constraints.EAN.Type;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Marwa
 *
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7950217207447904668L;
	@Id
	@Range(min=6190000000000L,
		max=6199999999999L,
		message="Invalid Barcode, barcode should be a number with '13' digits and starts with '619'"
		)
	//@EAN(type=Type.EAN13)
	@NotNull (message="barCode  is required")
	private Long barCode;
	@Column(unique=true)
	@NotNull (message="product name is required")
	private String name;
	@NotNull(message="Price is required")
	@Positive(message="The price should be positive number")
	private float price;
	@ManyToOne
	@NotNull
	private ProductCategory category;
	@OneToMany(mappedBy="product")
	@JsonIgnore
	private List <Ad> ads;
	@OneToMany(mappedBy="product",cascade=CascadeType.REMOVE)
	@JsonIgnore
	private List <UserProductViews> productUsersViews;
	

	public Long getBarCode() {
		return barCode;
	}
	public void setBarCode(Long barCode) {
		if (!isValidBarCode(barCode)) {
			throw new IllegalArgumentException("Invalid Barcode, barcode should be a number with '13' digits and starts with '619'");
		}
		this.barCode = barCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public List<Ad> getAds() {
		return ads;
	}
	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}	
	public List<UserProductViews> getProductUsersViews() {
		return productUsersViews;
	}
	public void setProductUsersViews(List<UserProductViews> productUsersViews) {
		this.productUsersViews = productUsersViews;
	}
	
	public Product() {
	}
	
	public Product(Long barCode,
			@NotNull(message = "product name is null") @UniqueElements(message = "Product name should be unique") String name,
			@NotNull @Positive(message = "The price should be positive number ") float price) {
		super();
		this.barCode = barCode;
		this.name = name;
		this.price = price;
	}
	public Product(Long barCode,
			@NotNull(message = "product name is null") @UniqueElements(message = "Product name should be unique") String name,
			@NotNull @Positive(message = "The price should be positive number ") float price,
			ProductCategory category) {
		super();
		this.barCode = barCode;
		this.name = name;
		this.price = price;
		this.category = category;
	}
	public Product(Long barCode, @NotNull(message = "product name is null") String name,
			@NotNull @Positive(message = "The price should be positive number ") float price, ProductCategory category,
			List<Ad> ads, List<UserProductViews> productUsersViews) {
		super();
		this.barCode = barCode;
		this.name = name;
		this.price = price;
		this.category = category;
		this.ads = ads;
		this.productUsersViews = productUsersViews;
	}


	
	
	@Override
	public String toString() {
		return "Product [barCode=" + barCode + ", name=" + name + ", price=" + price + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ads == null) ? 0 : ads.hashCode());
		result = prime * result + ((barCode == null) ? 0 : barCode.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((productUsersViews == null) ? 0 : productUsersViews.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (ads == null) {
			if (other.ads != null)
				return false;
		} else if (!ads.equals(other.ads))
			return false;
		if (barCode == null) {
			if (other.barCode != null)
				return false;
		} else if (!barCode.equals(other.barCode))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (productUsersViews == null) {
			if (other.productUsersViews != null)
				return false;
		} else if (!productUsersViews.equals(other.productUsersViews))
			return false;
		return true;
	}

	boolean isValidBarCode(Long code ) {
		if((code.toString().indexOf("619")!=0)||(code.toString().length()!=13)) return false;
		return true;
	}	
}
