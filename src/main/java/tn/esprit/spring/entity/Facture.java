package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "facture")
public class Facture implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

		
	@OneToOne
	private Panier panierDetail;
	
	@JsonIgnore
	@OneToOne(mappedBy="facture")
	private Delivery delivery;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Panier getPanierDetail() {
		return panierDetail;
	}


	public void setPanierDetail(Panier panierDetail) {
		this.panierDetail = panierDetail;
	}


	
	public Delivery getDelivery() {
		return delivery;
	}


	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((panierDetail == null) ? 0 : panierDetail.hashCode());
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
		Facture other = (Facture) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (panierDetail == null) {
			if (other.panierDetail != null)
				return false;
		} else if (!panierDetail.equals(other.panierDetail))
			return false;
		return true;
	}



	public Facture(Long id, Panier panierDetail) {
		super();
		this.id = id;
		this.panierDetail = panierDetail;
	}


	public Facture() {
		super();
	}
	
	
	@Override
	public String toString() {
		return id.toString();
	}
	
}
