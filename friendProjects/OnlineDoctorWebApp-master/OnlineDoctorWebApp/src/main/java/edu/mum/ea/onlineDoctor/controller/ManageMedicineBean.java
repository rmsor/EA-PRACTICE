/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.controller;

import edu.mum.ea.onlineDoctor.entity.Doctor;
import edu.mum.ea.onlineDoctor.entity.Medicine;
import edu.mum.ea.onlineDoctor.facade.MedicineFacade;
import edu.mum.ea.onlineDoctor.service.MedicineServiceBean;

import java.io.Serializable;
import java.util.List;
import javafx.scene.control.TableColumn;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;


/**
 *
 * @author Brook B
 */
@Named
@RequestScoped
    public class ManageMedicineBean implements Serializable{

    /**
     * Creates a new instance of ManageMedicineBean
     */
    
 
    private Medicine medicine;
    private List <Medicine> medicineList;
    @Inject
    private MedicineFacade medicineFacade;
    @EJB
    private MedicineServiceBean medicineSerivice;
  //  private MedicineServiceBean medicineService;
    private Medicine edittedMedicine;
    
    public ManageMedicineBean() {
    }

    @PostConstruct
    public void init() {
        
        this.medicineList=medicineFacade.findAll();
        //this.medicineList= medicineSerivice.allMedicines();
        this.medicine=new Medicine();
        
    }

    public MedicineFacade getMedicineFacade() {
        return medicineFacade;
    }

    public void setMedicineFacade(MedicineFacade medicineFacade) {
        this.medicineFacade = medicineFacade;
    }
    


    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public MedicineServiceBean getMedicineSerivice() {
        return medicineSerivice;
    }

    public void setMedicineSerivice(MedicineServiceBean medicineSerivice) {
        this.medicineSerivice = medicineSerivice;
    }

    public Medicine getEdittedMedicine() {
        return edittedMedicine;
    }

    public void setEdittedMedicine(Medicine edittedMedicine) {
        this.edittedMedicine = edittedMedicine;
    }
    
    
    
    
     public void addMedicine(){
              
        medicineSerivice.addMedicine(medicine);
                       
                
       FacesMessage msg = new FacesMessage("success", "Medicine Added");
       FacesContext.getCurrentInstance().addMessage(null, msg);
   
    } 
     
     
     
     
     public void onRowEdit(RowEditEvent event) {
                
        
        medicineFacade.edit(medicine);
                
       FacesMessage msg = new FacesMessage("success", "Medicine row Edited");
       FacesContext.getCurrentInstance().addMessage(null, msg);
   
    }
    public void onRowCancel(RowEditEvent event) {
        System.out.println("onRowCancel clicked");
        FacesMessage msg = new FacesMessage("success","Edit Cancelled" );
       FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(TableColumn.CellEditEvent event) {
        
        System.out.println("onRowEditCell clicked");
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
        //    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
        //    FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
     
     
     
}
