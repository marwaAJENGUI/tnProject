package tn.esprit.spring.frontcontroller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.service.interfaces.IDBFileStorageService;



@Named
@RequestScoped
@Join(path = "/testbarcode", to = "/testbarcode.jsf")
public class BarCodeFileView {  
	@Autowired
	IDBFileStorageService iDBFileStorageService;
	private UploadedFile file;
	private Long fileName;
  
	public UploadedFile getFile() {
        return file;
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

	public Long getFileName() {
		return fileName;
	}

	public void setFileName(Long fileName) {
		this.fileName = fileName;
	}

	public Long upload() {
        if (file != null) {
        	iDBFileStorageService.storeLocalFile(file);
        	fileName=Long.parseLong(iDBFileStorageService.getBarCode(file.getFileName()));
            FacesMessage message = new FacesMessage("Successful", file.getFileName()+ " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return fileName;
    } 
    
    public void handleFileUpload(FileUploadEvent event) {   	
    	FacesMessage msg = new FacesMessage("Successful upload" , "your barCode is "+ fileName);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
   
}