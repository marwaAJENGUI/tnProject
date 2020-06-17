package tn.esprit.spring.frontcontroller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.service.interfaces.IDBFileStorageService;


@ELBeanName(value = "fileUpdateView")
@Named
@RequestScoped
@Join(path = "/testFile", to = "/testFile.jsf")
public class FileUploadView {
	@Autowired
	IDBFileStorageService iDBFileStorageService;
	UploadedFile file;
	Long fileName;
	
	public void upload() {
		if (file != null)  {
        	iDBFileStorageService.storeFile(file, fileName);
        	FacesMessage message = new FacesMessage("File uploaded", file.getFileName()+ " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
       }
	}

	public IDBFileStorageService getiDBFileStorageService() {
		return iDBFileStorageService;
	}

	public void setiDBFileStorageService(IDBFileStorageService iDBFileStorageService) {
		this.iDBFileStorageService = iDBFileStorageService;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		FacesContext context = FacesContext.getCurrentInstance();
		String imageName=context.getExternalContext().getRequestParameterMap().get("fileName");
		 fileName =Long.parseLong( imageName);
		this.file = file;
	}


}
