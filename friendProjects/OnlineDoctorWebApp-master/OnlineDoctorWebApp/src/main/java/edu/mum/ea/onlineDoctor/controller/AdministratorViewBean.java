/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.onlineDoctor.controller;

import edu.mum.ea.onlineDoctor.entity.Address;
import edu.mum.ea.onlineDoctor.entity.Patient;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author HailelulLakew
 */

@Named("AdminBean")  
@RequestScoped
public class AdministratorViewBean implements Serializable{

    /**
     * Creates a new instance of ViewPatientAdministrator
     */
    private Patient patient;
    private Address address;
    
    
    @PostConstruct    
    public void init() { 
        setAddress(new Address());
        setPatient (new Patient());   
        
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = Calendar.getInstance().getTime(); 
        
        getAddress().setCity("Fairfield");       
        getAddress().setState("Iowa");
     //   getAddress().setStreet("North 1000");        
        patient.setAddress(getAddress());          
        patient.setDateofBirth(today);
        patient.setFirstName("David");
        patient.setLastName("Johanson");
        patient.setCellPhoneNo("45238903456");        
        patient.setHomePhoneNo("623689999999");
        patient.setWorkPhoneNo("15634688888");
//        patient.setUserName("dfdfd");
//        patient.setUserPassword("fdff");
        //System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        
        
        
        
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    
}
