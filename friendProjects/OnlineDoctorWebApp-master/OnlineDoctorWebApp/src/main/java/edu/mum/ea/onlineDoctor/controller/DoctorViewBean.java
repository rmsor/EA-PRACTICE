/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.controller;

import edu.mum.ea.onlineDoctor.entity.Address;
import edu.mum.ea.onlineDoctor.entity.Doctor;
import edu.mum.ea.onlineDoctor.entity.MedicalRecord;
import edu.mum.ea.onlineDoctor.entity.Patient;
import edu.mum.ea.onlineDoctor.facade.AppointmentFacade;
import edu.mum.ea.onlineDoctor.facade.PatientFacade;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author HailelulLakew
 */
@Named("DoctorView")
@RequestScoped
public class DoctorViewBean implements Serializable {

    /**
     * Creates a new instance of viewPatientDoctor
     */
    private List<Doctor> doctors;
    private ArrayList<Patient> patients;
    private List<MedicalRecord> medicalRecords;

    @EJB
    private PatientFacade patientFacade;
    @EJB
    private AppointmentFacade appointmentFacade;

    @PostConstruct
    public void init() {
        Address address = new Address();
        Doctor doctor = new Doctor();

        MedicalRecord medicalRecord = new MedicalRecord();
        setMedicalRecords(new ArrayList<MedicalRecord>());
        doctors = new ArrayList<Doctor>();

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = Calendar.getInstance().getTime();
        medicalRecord.setRemarks("Need Attantion");
        medicalRecord.setRecordDate(today);
        medicalRecord.setSymptoms("Head ache and eching");
        //  doctor.getMedicalRecords().add(medicalRecord);
        address.setCity("Fairfield");

        address.setState("Iowa");
        //address.setStreetAddress("North 1000");        
        doctor.setAddress(address);
        doctor.setDateofBirth(today);
        doctor.setFirstName("David");
        doctor.setLastName("Johanson");
        doctor.setCellPhoneNo("45238903456");
        // doctor.setCellPhoneNo("45238903");
        doctor.setHomePhoneNo("623689999");
        doctor.setWorkPhoneNo("156346888");
        doctors.add(doctor);
        System.out.println(doctors.size());
    }

    public void loadPatient() {
        setPatients(patientFacade.loadPatient());

    }

    /**
     * @return the doctors
     */
    public List<Doctor> getDoctors() {
        return doctors;
    }

    /**
     * @param doctors the doctors to set
     */
    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    /**
     * @return the medicalRecords
     */
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    /**
     * @param medicalRecords the medicalRecords to set
     */
    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    /**
     * @return the patientFacade
     */
    public PatientFacade getPatientFacade() {
        return patientFacade;
    }

    /**
     * @param patientFacade the patientFacade to set
     */
    public void setPatientFacade(PatientFacade patientFacade) {
        this.patientFacade = patientFacade;
    }

    /**
     * @return the patients
     */
    public ArrayList<Patient> getPatients() {
        return patients;
    }

    /**
     * @param patients the patients to set
     */
    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

}
