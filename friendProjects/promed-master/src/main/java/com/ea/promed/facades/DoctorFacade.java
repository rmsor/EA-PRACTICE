/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.facades;

import com.ea.promed.entities.Appointment;
import com.ea.promed.entities.Doctor;
import com.ea.promed.entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kunda_000
 */

@Stateless
public class DoctorFacade extends AbstractFacade<Doctor> {
    
    @PersistenceContext(unitName = "promedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DoctorFacade() {
        super(Doctor.class);
    }
    
    public List<Doctor> listAllDoctors()
    {
        return getEntityManager().createQuery("SELECT d FROM Doctor d ORDER BY d.id DESC").getResultList();
    }
    
    
    
    public Doctor getDoctorByUser(User user)
    {
        return (Doctor) getEntityManager().createQuery("SELECT d FROM Doctor d WHERE d.user = ?1").setParameter(1, user).getSingleResult();
    }
    
    public List<Doctor> getFreeDoctors()
    {
        return getEntityManager().createQuery("SELECT d FROM Doctor d").getResultList();
    }
    
    
    public boolean updateDoctorTest(Doctor d){
        try {
             Doctor doctor=em.find(Doctor.class, d.getId());
             doctor.setCity(d.getCity());
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
    
    public List<Appointment> listDoctorAppointments(Doctor doctor)
    {
        return getEntityManager().createQuery("SELECT a FROM Appointment a INNER JOIN a.patient p WHERE a.doctor.id = ?1 AND a.status > 1 ORDER BY a.id DESC").setParameter(1, doctor.getId()).getResultList();
    }
    
    
    
    
    
}
