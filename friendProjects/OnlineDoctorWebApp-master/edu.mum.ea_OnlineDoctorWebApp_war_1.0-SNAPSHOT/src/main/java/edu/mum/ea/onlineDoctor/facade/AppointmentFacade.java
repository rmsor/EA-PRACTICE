/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.facade;

import edu.mum.ea.onlineDoctor.entity.Appointment;
import edu.mum.ea.onlineDoctor.entity.Doctor;
import edu.mum.ea.onlineDoctor.entity.Patient;
import edu.mum.ea.onlineDoctor.service.AppointmentServiceBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static javafx.scene.input.KeyCode.T;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Stella
 */
@Stateless
public class AppointmentFacade extends AbstractFacade<Appointment> {
    @PersistenceContext(unitName = "edu.mum.ea_OnlineDoctorWebApp_war_1.0-SNAPSHOTPU")
     private EntityManager em;
   

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

    public AppointmentFacade() {
        super(Appointment.class);
    }
    
    public List<Appointment> findPatientAppointments(Patient patient) {

        Query query = em.createQuery("SELECT a FROM Appointment a WHERE a.patientInAppointment=:patient", Appointment.class);

        List<Appointment> apps;
        query.setParameter("patient", patient);
        apps = (List<Appointment>) query.getResultList();

        return apps;
    }
    
     public List<Appointment> findDoctorAppointments(Doctor doctor) {

        Query query = em.createQuery("SELECT a FROM Appointment a WHERE a.doctor=:doctor", Appointment.class);

        List<Appointment> apps;
        query.setParameter("doctor", doctor);
        apps = (List<Appointment>) query.getResultList();

        return apps;
    }
    
    public void create(Appointment entity) {
        getEntityManager().persist(entity);
        //appointmentServiceBean.create(entity);
    }
    
    
}
