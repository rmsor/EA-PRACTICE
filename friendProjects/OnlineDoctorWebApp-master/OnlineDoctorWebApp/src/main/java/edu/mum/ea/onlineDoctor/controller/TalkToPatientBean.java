/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.controller;

import edu.mum.ea.onlineDoctor.entity.Appointment;
import edu.mum.ea.onlineDoctor.entity.Doctor;
import edu.mum.ea.onlineDoctor.serviceI.AppointmentServiceBeanLocal;
import edu.mum.ea.onlineDoctor.serviceI.DoctorServiceBeanLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Senai
 */
@Named
@RequestScoped
public class TalkToPatientBean implements Serializable {

    private Long selectedAppointmentID;
    private List<Appointment> appointments;
    private Appointment selectedAppointment;
    private Doctor doctor;

    @Inject
    private AppointmentServiceBeanLocal appointmentService;
    @Inject
    private DoctorServiceBeanLocal doctorService;

    @PostConstruct
    public void init() {

        //getLoggedInUser 
        //hard code: replace this with loggedin user later
        Long id = Long.valueOf(4);

        doctor = doctorService.getDoctorById(id);

        //get available doctor's schedules
        appointments = appointmentService.findDoctorAppointments(doctor);
        System.out.println("Appointment " + appointments.size());
//        selectedAppointment = new Appointment();
        selectedAppointment = appointments.get(0);

    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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

    public void getAppointmentByID() {

        selectedAppointment = appointmentService.findAppointmentByID(selectedAppointmentID);
    }

}
