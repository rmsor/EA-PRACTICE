/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.controller;

import edu.mum.ea.onlineDoctor.entity.AppUser;
import edu.mum.ea.onlineDoctor.entity.Patient;
import edu.mum.ea.onlineDoctor.facade.PatientFacade;
import edu.mum.ea.onlineDoctor.service.EmailSender;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fetiya
 */
@Named
@SessionScoped
public class EditPatientBean implements Serializable {

    @EJB
    private PatientFacade patientFacacde;

    private Patient patient = new Patient();//(Patient)patientFacacde.find(3);
    private String newPassword;
    private String newPasswordConfirm;
    private String currentPassword;

    public EditPatientBean() {

        //   Patient patient2 =;
    }

    @PostConstruct
    private void init() {

        //for test user 1  , hard coded to be replaced by logged in user
        //Long id = Long.valueOf(7);
        patient =(Patient) getCurrentUser();

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

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String updatePatient() {

        patientFacacde.edit(patient);

        return "patientInfoSuccess";
    }

    
     public String updateAccount() {

        patientFacacde.edit(patient);

        return "patientInfoSuccess";
    }
    public void validateDateofBirth(FacesContext fc, UIComponent component, Object value) {

        Date dateOfBirth = (Date) value;

        if (dateOfBirth.after(new Date())) {

            FacesMessage msg
                    = new FacesMessage(" Date of Birth should be in the past");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);
        }
    }
    @EJB
    EmailSender emailSender;

    public String changePassword() throws Exception {

        if (currentPassword.equals(patient.getCredential().getPassword()) && newPassword.equals(newPasswordConfirm)) {
            patient.getCredential().setPassword(newPassword);
            patientFacacde.edit(patient);

            FacesContext context = FacesContext.getCurrentInstance();
            String message;
            message = "Dear " + patient.getFirstName() + " , \n"
                    + "\nYour password for Online Doctor website has been changed."
                    + "We are sending you this notice for your protection. \nIf it wasn't you who made the change please contact us immediately \n"
                    + " \n\n Regards,\n\n"
                    + "MUM Online Doctor Administrator";
            emailSender.sendEmail(message, "Password changed",
                    patient.getEmail(), "mumonlinedoctor@gmail.com");

            return "patientInfoSuccess";
        } else {
            return "editPatientInfo";
        }

    }

    public String savePatient() {
        System.out.println("pre save method");
        this.patientFacacde.create(patient);
        System.out.println("post save method");
        return "index";
    }
    
    public String clickMe()
    {
        System.out.println("Clck me clicked");
        
        return "home";
    }
    
        private AppUser getCurrentUser() {

        FacesContext context = FacesContext.getCurrentInstance();
     
        HttpSession session = (HttpSession)
        FacesContext.getCurrentInstance().getCurrentInstance().getExternalContext().getSession(false);
        AppUser patient=(AppUser)session.getAttribute("currentUser");
        System.out.println("the loggedin user is :" +patient.getFirstName()+","+patient.getLastName());
        return patient;
       
    
    }
}
