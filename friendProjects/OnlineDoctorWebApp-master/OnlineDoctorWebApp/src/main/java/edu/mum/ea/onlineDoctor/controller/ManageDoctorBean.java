/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.controller;

import edu.mum.ea.onlineDoctor.entity.Address;
import edu.mum.ea.onlineDoctor.entity.Credential;
import edu.mum.ea.onlineDoctor.entity.Doctor;
import edu.mum.ea.onlineDoctor.facade.DoctorFacade;


import java.io.Serializable;

import java.util.List;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.annotation.PostConstruct;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import javax.inject.Named;
import org.primefaces.event.RowEditEvent;



/**
 *
 * @author Brook B
 */
@Named
@RequestScoped
public class ManageDoctorBean implements Serializable {


    
    
    private Doctor selectedoctor;
    private List<Doctor> doctorList;
    private Address address;

    private Credential credential;
    
    private Doctor edittedDoctor;
    @Inject
    private DoctorFacade doctorFacacde;
    
//    @Inject
//    private DoctorServiceBeanLocal doctorServiceBean;
    
    @PostConstruct
    public void init() {
        doctorList = doctorFacacde.findAll();
        selectedoctor=new Doctor();
        address=new Address();
        credential=new Credential();
        
        //doctorServiceBean=new DoctorServiceBean();
        System.out.println("Doctor and address initialized : postconstruct");
    }

   
    public DoctorFacade getDoctorFacacde() {
        return doctorFacacde;
    }

    public void setDoctorFacacde(DoctorFacade doctorFacacde) {
        this.doctorFacacde = doctorFacacde;
    }

    public Doctor getSelectedoctor() {
        return selectedoctor;
    }

    public void setSelectedoctor(Doctor selectedoctor) {
        this.selectedoctor = selectedoctor;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }
    

    public void onRowEdit(RowEditEvent event) {
                
        edittedDoctor = ((Doctor)event.getObject());
      
        //doctorServiceBean.updateDoctor(selectedoctor);
        doctorFacacde.edit(edittedDoctor);
        //System.out.println(edittedDoctor.getFirstName());
        FacesMessage msg = new FacesMessage("success", "doctor row Edited");//((Doctor) event.getObject()).getId());
       FacesContext.getCurrentInstance().addMessage(null, msg);
   
    }
    public void onRowCancel(RowEditEvent event) {
        System.out.println("onRowCancel clicked");
        FacesMessage msg = new FacesMessage("hi","Edit Cancelled" );
       FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        
        System.out.println("onRowEditCell clicked");
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
        //    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
        //    FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
//    public void updateDoctor() {
//        doctorFacacde.create(selectedoctor);
//        selectedCar = null;
//    }
}
