/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.controller;

import edu.mum.ea.onlineDoctor.entity.Appointment;
import edu.mum.ea.onlineDoctor.entity.Patient;
import edu.mum.ea.onlineDoctor.serviceI.AppointmentServiceBeanLocal;
import edu.mum.ea.onlineDoctor.serviceI.PatientServiceBeanLocal;
import java.io.Serializable;
import java.util.List;
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
public class TalkToDoctorBean implements Serializable {

    private Long selectedAppointmentID;
    private List<Appointment> appointments;
    private Appointment selectedAppointment;
    private Patient patient;

    @EJB
    private AppointmentServiceBeanLocal appointmentService;
    @EJB
    private PatientServiceBeanLocal patientService;

    @PostConstruct
    public void init() {

      //getLoggedInUser 
        //hard code: replace this with loggedin user later
        Long id = Long.valueOf(7);

        patient = patientService.getPatientById(id);//.find(id);//.find(id);//
        System.out.println("INFO TalkToDoctorBean - Patient found");
        //get available patient's appointemnts

        appointments = appointmentService.findPatientAppointments(patient);
        if (appointments.size() > 0) {
            System.out.println("INFO TalkToDoctorBean - Patient Appointment found");
        }
        selectedAppointment = new Appointment();

        selectedAppointment = appointments.get(0);

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

    public void findAppointmentByID() {

        selectedAppointment = appointmentService.findAppointmentByID(selectedAppointmentID);

        //  selectedAppointment=appointmentService.find(selectedAppointmentID);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
