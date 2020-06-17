package tn.esprit.spring.frontcontroller;

import java.text.SimpleDateFormat;  
import java.util.Date;  
import javax.faces.application.FacesMessage;  
import javax.faces.bean.ManagedBean;  
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;  


@ManagedBean  
public class Calendar {  
private Date date;  
public void onDateSelect(SelectEvent event) {  
FacesContext facesContext = FacesContext.getCurrentInstance();  
SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));  
}  
public void click() {  
PrimeFaces requestContext = PrimeFaces.current();  
requestContext.ajax().update("form:display");  
requestContext.executeScript("PF('dlg').show()");  
}  
public Date getDate() {  
return date;  
}  
public void setDate(Date date) {  
this.date = date;  
}  
}  
