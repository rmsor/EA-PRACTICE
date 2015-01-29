/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.controller;

import edu.mum.ea.onlineDoctor.entity.AppUser;
import edu.mum.ea.onlineDoctor.entity.Appointment;
import edu.mum.ea.onlineDoctor.entity.Patient;
import edu.mum.ea.onlineDoctor.facade.AppointmentFacade;
import edu.mum.ea.onlineDoctor.facade.PatientFacade;
import edu.mum.ea.onlineDoctor.serviceI.AppointmentServiceBeanLocal;
import edu.mum.ea.onlineDoctor.serviceI.PatientServiceBeanLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;


import javax.inject.Named;

/**
 *
 * @author Fetiya
 */
@Named
@RequestScoped
public class TalkToDoctorBean implements Serializable{

    private Long selectedAppointmentID;

    private List<Appointment> appointments;

    private Appointment selectedAppointment;

    @EJB
    private AppointmentServiceBeanLocal appointmentService;

    @EJB 
    private PatientServiceBeanLocal patientService;
    

    
    private Patient patient;
    
    
   

    @PostConstruct
    public void init() {
        
      //getLoggedInUser 
      //hard code: replace this with loggedin user later
        Long id=Long.valueOf(5);
        
        patient=patientService.getPatientById(id);//.find(id);//.find(id);//
        System.out.println("Patient");
        //get available patient's appointemnts
     
      
        appointments=appointmentService.findPatientAppointments(patient);
       
        selectedAppointment = new Appointment();
        
        
        
    }

    public Long getSelectedAppointmentID() {
        return selectedAppointmentID;
    }

    public void setSelectedAppointmentID(Long selectedAppointmentID) {
        this.selectedAppointmentID = selectedAppointmentID;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    public void setSelectedAppointment(Appointment selectedAppointment) {
        this.selectedAppointment = selectedAppointment;
    }
    
    public void findAppointmentByID()
    {
        
      selectedAppointment= appointmentService.findAppointmentByID(selectedAppointmentID);
        
      //  selectedAppointment=appointmentService.find(selectedAppointmentID);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

  
   
    

}
