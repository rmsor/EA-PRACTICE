/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.controller;

import edu.mum.ea.onlineDoctor.entity.Doctor;
import edu.mum.ea.onlineDoctor.facade.DoctorFacade;


import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Brook B
 */
@ManagedBean
@SessionScoped
public class ManageDoctorBean implements Serializable {

    @EJB
    private DoctorFacade doctorFacacde;

    private Doctor selectedoctor;
    private List<Doctor> doctorList;

    @PostConstruct
    public void init() {
        doctorList = doctorFacacde.findAll();
        selectedoctor=new Doctor();
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
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
    
   



    public String updateDoctor() {

        doctorFacacde.edit(selectedoctor);

        return "doctorInfoSuccess";
    }
    
    public String addDoctor(){
        System.out.println("action methodddddddddddddddddddddddddd");
        doctorFacacde.create(selectedoctor);
                System.out.println("enction methodddddddddddddddddddddddddd");
                
                return "index";
    } 
}
