/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.controller;

import edu.mum.ea.onlineDoctor.entity.Address;
import edu.mum.ea.onlineDoctor.entity.AppRole;
import edu.mum.ea.onlineDoctor.entity.Credential;
import edu.mum.ea.onlineDoctor.entity.Patient;
import edu.mum.ea.onlineDoctor.facade.AppRoleFacade;
import edu.mum.ea.onlineDoctor.facade.CredentialFacade;
import edu.mum.ea.onlineDoctor.service.EmailSender;
import edu.mum.ea.onlineDoctor.serviceI.PatientServiceBeanLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Fetiya
 */
@Named
@RequestScoped
public class PatientBean implements Serializable {

//    @EJB
//    private AddressFacade addressFacade;
//
//    @EJB
//    private PatientFacade patientFacacde;
    @EJB
    private PatientServiceBeanLocal patientService;
    @EJB
    private AppRoleFacade roleFacade;
    @EJB
    private CredentialFacade credentialfacade;
    @EJB
    EmailSender emailSender;

    private Address address;

    private Patient patient;
    private AppRole role;
    private Credential credential;

    @PostConstruct
    public void init() {
        this.patient = new Patient();
        address = new Address();
        this.role = roleFacade.find("Patient");
        this.credential = new Credential();

    }
//
//    public PatientFacade getPatientFacacde() {
//        return patientFacacde;
//    }
//
//    public void setPatientFacacde(PatientFacade patientFacacde) {
//        this.patientFacacde = patientFacacde;
//    }

    public CredentialFacade getCredentialfacade() {
        return credentialfacade;
    }

    public void setCredentialfacade(CredentialFacade credentialfacade) {
        this.credentialfacade = credentialfacade;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public AppRole getRole() {
        return role;
    }

    public void setRole(AppRole role) {
        this.role = role;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public PatientServiceBeanLocal getPatientService() {
        return patientService;
    }

    public void setPatientService(PatientServiceBeanLocal patientService) {
        this.patientService = patientService;
    }

    public AppRoleFacade getRoleFacade() {
        return roleFacade;
    }

    public void setRoleFacade(AppRoleFacade roleFacade) {
        this.roleFacade = roleFacade;
    }

    public String updatePatient() {

//        patientFacacde.edit(patient);
        return "patientInfoSuccess";
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

//     public void validate(FacesContext arg0, UIComponent arg1, Object brandName)
//            throws ValidatorException {
//        Credential credential1 = null;
//        try{
//            credential1 = credentialfacade.f.getBrandByBrandName(brandName.toString());
//        }
//        catch(Exception ex){}
//        if(brand != null){
//            tempBean.setBrandDescription(brand.getBrandDescription());
//            tempBean.setBrandName(brand.getBrandName());
//            tempBean.setBrandId(brand.getBrandId());
//            FacesMessage msg = 
//                    new FacesMessage("Already Exists", 
//                            "Re-Enter Brand Name");
//                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//                throw new ValidatorException(msg);
//        }
//    }
    public String addPatient() throws Exception {

        patient.setCredential(credential);
        credential.setRole(role);
        patient.setAddress(address);
        //String sinupresualt = patientService.signupPatient(patient);
        String message;
            message = "Dear " + patient.getFirstName() + " , \n"
                    + "\nYour have successfully Sign up in Online Doctor website."
                    + "We are sending you this notice for your protection. \nIf it wasn't you who made the change please contact us immediately \n"
                    + " \n\n Regards,\n\n"
                    + "MUM Online Doctor Administrator";
            emailSender.sendEmail(message, "welcome to Online Doctor website.",
                    patient.getEmail(), "mumonlinedoctor@gmail.com");
            System.out.println("this is patient's email" + patient.getEmail());
        return "login";
    }
}
