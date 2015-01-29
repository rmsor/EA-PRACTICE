/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.controller;

import edu.mum.ea.onlineDoctor.entity.Patient;
import edu.mum.ea.onlineDoctor.facade.PatientFacade;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Fetiya
 */
@ManagedBean
@SessionScoped
public class EditPatientBean implements Serializable {

    @EJB
    private PatientFacade patientFacacde;

    private Patient patient = new Patient();//(Patient)patientFacacde.find(3);
    private String newPassword;

    public EditPatientBean() {

        //   Patient patient2 =;
    }

    @PostConstruct
    private void init() {

        //for test user 1  , hard coded to be replaced by logged in user
        Long id = Long.valueOf(5);
        patient = patientFacacde.find(id);

//        System.out.println("Patient Name " + patient.getFirstName());
//        System.out.println("patient address street" + patient.getAddress().getStreet());

    }

    public PatientFacade getPatientFacacde() {
        return patientFacacde;
    }

    public void setPatientFacacde(PatientFacade patientFacacde) {
        this.patientFacacde = patientFacacde;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String updatePatient() {

        patientFacacde.edit(patient);

        return "patientInfoSuccess";
    }

    public void validateDateofBirth(FacesContext fc, UIComponent component, Object value) {

        Date dateOfBirth = (Date) value;

        if (dateOfBirth.after(new Date())) {
            throw new ValidatorException(
                    new FacesMessage("Date of Birth should be in the past"));

        }

    }

    public String savePatient() {
        System.out.println("pre save method");
        this.patientFacacde.create(patient);
        System.out.println("post save method");
        return "index";
    }
}
