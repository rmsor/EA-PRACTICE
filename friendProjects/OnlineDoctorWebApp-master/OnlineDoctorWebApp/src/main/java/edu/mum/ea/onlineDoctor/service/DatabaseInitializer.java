/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.service;

import edu.mum.ea.onlineDoctor.serviceI.DatabaseInitializerLocal;
import edu.mum.ea.onlineDoctor.entity.Address;
import edu.mum.ea.onlineDoctor.entity.Administrator;
import edu.mum.ea.onlineDoctor.entity.AppRole;
import edu.mum.ea.onlineDoctor.entity.Appointment;
import edu.mum.ea.onlineDoctor.entity.Credential;
//import edu.mum.ea.onlineDoctor.entity.Credential;
import edu.mum.ea.onlineDoctor.entity.Doctor;
import edu.mum.ea.onlineDoctor.entity.Gender;
import edu.mum.ea.onlineDoctor.entity.Medicine;
import edu.mum.ea.onlineDoctor.entity.Patient;
import edu.mum.ea.onlineDoctor.facade.AddressFacade;
import edu.mum.ea.onlineDoctor.facade.AdministratorFacade;
import edu.mum.ea.onlineDoctor.facade.AppRoleFacade;
import edu.mum.ea.onlineDoctor.facade.AppointmentFacade;
import edu.mum.ea.onlineDoctor.facade.CredentialFacade;
import edu.mum.ea.onlineDoctor.facade.DoctorFacade;
import edu.mum.ea.onlineDoctor.facade.MedicineFacade;
import edu.mum.ea.onlineDoctor.facade.PatientFacade;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Senai
 */
@Singleton
@Startup
public class DatabaseInitializer implements DatabaseInitializerLocal {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private AdministratorFacade adminFacade;
    @Inject
    private AddressFacade addressFacade;
    @Inject
    private DoctorFacade doctorFacade;
    @Inject
    private PatientFacade patientFacade;
    @Inject
    private AppRoleFacade appRoleFacade;
    @Inject
    private CredentialFacade credentailFacade;
    @Inject
    private AppointmentFacade appointmentFacade;
    
    @Inject

    private MedicineFacade medicineFacade;
  
    

    

 @PersistenceContext(unitName = "edu.mum.ea_OnlineDoctorWebApp_war_1.0-SNAPSHOTPU")
    @PostConstruct
    public void init() {
        System.out.println("I am in Database Initializer Singletonnnnnnnnnnnnnnnnnnnn");
        
        //creating roles
        AppRole role1=new AppRole();
        role1.setName("Admin");
        appRoleFacade.create(role1);
       
        
        AppRole role2=new AppRole();
        role2.setName("Patient");
        appRoleFacade.create(role2);
        
        AppRole role3=new AppRole();
        role3.setName("Doctor");
        appRoleFacade.create(role3);
        
        Medicine medicine= new Medicine();
        medicine.setBiologicalName("Mebendazol");
        
        //Creating First Administrator
        Administrator admin1 = new Administrator();
//        admin1.setUserName("Admin");
//        admin1.setPassword("admin");
        admin1.setFirstName("Biruk");
        admin1.setLastName("Berhanu");
        admin1.setGender(Gender.MALE);
        admin1.setDateofBirth(new Date());
        admin1.setCellPhoneNo("001(641)483-8745");
        admin1.setHomePhoneNo("001(641)546-2345");
        admin1.setWorkPhoneNo("001(641)893-2345");

        Address adminAddress = new Address();
        adminAddress.setStreet("4th street");
        adminAddress.setCity("Fairfield");
        adminAddress.setState("Iowa");
        adminAddress.setZipCode("55257");
      
        Credential credential1=new Credential();
        credential1.setUser(admin1);
        credential1.setUserName("Admin");
        credential1.setPassword("admin");
        credential1.setRole(role1);
        admin1.setCredential(credential1);
//        credentailFacade.create(credential1);

        admin1.setAddress(adminAddress);

        
        //Creating First Doctor
        Doctor doc1 = new Doctor();
//        doc1.setUserName("Haile");
//        doc1.setPassword("haile");
        doc1.setFirstName("Haile");
        doc1.setLastName("Teshome");
        doc1.setGender(Gender.MALE);
        doc1.setDateofBirth(new Date());
        doc1.setCellPhoneNo("001(641)483-8745");
        doc1.setHomePhoneNo("001(641)546-2345");
        doc1.setWorkPhoneNo("001(641)893-2345");

        Address doc1Address = new Address();
        doc1Address.setStreet("4th street");
        doc1Address.setCity("Fairfield");
        doc1Address.setState("Iowa");
        doc1Address.setZipCode("55257");

        doc1.setAddress(doc1Address);
        
        Credential credential2=new Credential();
        credential2.setUser(doc1);
        credential2.setUserName("Haile");
        credential2.setPassword("haile");
        credential2.setRole(role3);
        doc1.setCredential(credential2);
//        credentailFacade.create(credential2);

        
        //Creating First Patient 
        Patient patient1 = new Patient();
//        patient1.setUserName("Stella");
//        patient1.setPassword("stella");
        patient1.setFirstName("Stella");
        patient1.setLastName("Berhe");
        patient1.setGender(Gender.FEMALE);
        patient1.setEmail("fetiyab@gmail.com");
        patient1.setDateofBirth(new Date());
        patient1.setCellPhoneNo("001(641)483-8745");
        patient1.setHomePhoneNo("001(641)546-2345");
        patient1.setWorkPhoneNo("001(641)893-2345");
        
        Address patient1Address = new Address();
        patient1Address.setStreet("4th street");
        patient1Address.setCity("Fairfield");
        patient1Address.setState("Iowa");
        patient1Address.setZipCode("55257");

        patient1.setAddress(patient1Address);
        
         Credential credential3=new Credential();
        credential3.setUser(patient1);
        credential3.setUserName("Stella");
        credential3.setPassword("stella");
        credential3.setRole(role2);
        patient1.setCredential(credential3);
//        credentailFacade.create(credential3);
        
        Appointment apt= new Appointment();
        apt.setAppointmentDate(new Date());
        apt.setDoctor(doc1);
        apt.setPatientInAppointment(patient1);

        apt.setEndTime(new Date());
        apt.setStartTime(new Date());
        adminFacade.create(admin1);
        doctorFacade.create(doc1);
        patientFacade.create(patient1);
        
         appointmentFacade.create(apt);
         medicineFacade.create(medicine);

    }
}
