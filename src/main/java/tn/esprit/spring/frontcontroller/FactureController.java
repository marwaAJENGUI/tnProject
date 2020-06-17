package tn.esprit.spring.frontcontroller;

import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.repository.FactureRepository;


@Scope(value = "session")
@Controller(value = "factureFController")
@ELBeanName(value = "factureFController")
public class FactureController {
	
	@Autowired
	FactureRepository facRepo;
	
	private List<Facture> factures;
	
	private long selectFactureId;
	
	public List<Facture> getFactures() {
		factures = facRepo.findFactures();
		return factures;
	}

	public long getSelectFactureId() {
		return selectFactureId;
	}

	public void setSelectFactureId(long selectFactureId) {
		this.selectFactureId = selectFactureId;
	}
	
	

}
