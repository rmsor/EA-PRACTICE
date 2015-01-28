/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.service;

import edu.mum.ea.onlineDoctor.entity.Medicine;
import edu.mum.ea.onlineDoctor.facade.MedicineFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Brook B
 */
@Stateless
public class MedicineServiceBean {

     @EJB
    private MedicineFacade medicineFacade;
    

//    public List<Medicine> allMedicines(){
//        return medicineFacade.findAll();
//    } 
     
    public void addMedicine(Medicine newMedicine) {

        medicineFacade.create(newMedicine);
        
        FacesMessage msg = new FacesMessage("success", "new doctor added");
        FacesContext.getCurrentInstance().addMessage(null, msg);   
    }
    
    public void updateMedicine(Medicine newMedicine){
        
        medicineFacade.edit(newMedicine);
        
        FacesMessage msg = new FacesMessage("success", "doctor updated");
        FacesContext.getCurrentInstance().addMessage(null, msg);   
    }
}
