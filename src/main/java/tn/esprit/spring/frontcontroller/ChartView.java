	package tn.esprit.spring.frontcontroller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.entity.Driver;
import tn.esprit.spring.service.interfaces.IDriverService;

@Named
@RequestScoped
public class ChartView implements Serializable {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	IDriverService iDriverService;
	 private PieChartModel pieModel;
	 
	   @PostConstruct
	    public void init() {
	        createPieModels();
	    }
	   
	    
	    public PieChartModel getPieModel1() {
	        return pieModel;
	    }
	    
	    private void createPieModels() {
	    	
	        List<Driver> drivers = iDriverService.findAllDrivers();
	    	pieModel = new PieChartModel();	 
	        pieModel.setTitle("Task Completed");
	        pieModel.setLegendPosition("e");
	        pieModel.setShadow(true);
	       

	        if (drivers!=null) {
	    	for (int i=0 ; i<drivers.size();i++) {
	    	pieModel.set(drivers.get(i).getFirstName(),drivers.get(i).getTaskCompleted());
	    	}
	        }
//	        pieModel.set("Error",0);
	    }
	    
	    public String refreshPie() {
	        List<Driver> drivers = iDriverService.findAllDrivers();	        
	        if (drivers!=null) {
	    	for (int i=0 ; i<drivers.size();i++) {
	    	pieModel.set(drivers.get(i).getFirstName(),drivers.get(i).getTaskCompleted());
	    	}}
			return "/admin/Driver/DriverStatistics.jsf?faces-redirect=true";
	    }


		public PieChartModel getPieModel() {
			return pieModel;
		}		
}
