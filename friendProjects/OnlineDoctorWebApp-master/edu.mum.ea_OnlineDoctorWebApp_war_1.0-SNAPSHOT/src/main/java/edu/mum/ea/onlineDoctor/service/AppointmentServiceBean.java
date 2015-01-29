/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.onlineDoctor.service;

import edu.mum.ea.onlineDoctor.entity.Appointment;
import edu.mum.ea.onlineDoctor.entity.Doctor;
import edu.mum.ea.onlineDoctor.entity.Patient;
import edu.mum.ea.onlineDoctor.facade.AppointmentFacade;
import edu.mum.ea.onlineDoctor.serviceI.AppointmentServiceBeanLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author HailelulLakew
 */
@Stateless
public class AppointmentServiceBean implements AppointmentServiceBeanLocal  {
    @EJB 
 private AppointmentFacade appointmentFacade;
    
   public void create(Appointment entity) {
          appointmentFacade.create(entity);
    } 

    /**
     * @return the appointmentFacade
     */
    public AppointmentFacade getAppointmentFacade() {
        return appointmentFacade;
    }

    /**
     * @param appointmentFacade the appointmentFacade to set
     */
    public void setAppointmentFacade(AppointmentFacade appointmentFacade) {
        this.appointmentFacade = appointmentFacade;
    }

 

    @Override
    public String createAppointment(Appointment appointment) {

        appointmentFacade.create(appointment);

        return "";
    }

    @Override
    public List<Appointment> findAllAppointments() {

        List<Appointment> apps = appointmentFacade.findAll();

        return apps;
    }

    @Override
    public Appointment findAppointmentByID(Long id) {

        return appointmentFacade.find(id);
    }

    @Override
    public List<Appointment> findDoctorAppointments(Doctor doctor) {

        return appointmentFacade.findDoctorAppointments(doctor);
    }

    @Override
    public List<Appointment> findPatientAppointments(Patient patient) {
        return appointmentFacade.findPatientAppointments(patient);

    }
}
