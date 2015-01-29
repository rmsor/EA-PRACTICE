/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.facades;

import com.ea.promed.entities.Appointment;
import com.ea.promed.entities.Doctor;
import com.ea.promed.entities.Nurse;
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
public class NurseFacade extends AbstractFacade<Nurse> {
    @PersistenceContext(unitName = "promedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NurseFacade() {
        super(Nurse.class);
    }
    
    
    public List<Nurse> listAllNurses()
    {
        return getEntityManager().createQuery("SELECT n FROM Nurse n ORDER BY n.id DESC").getResultList();
    }
    
    
    public Nurse getNurseByUser(User user)
    {
        return (Nurse) getEntityManager().createQuery("SELECT n FROM Nurse n WHERE n.user = ?1").setParameter(1, user).getSingleResult();
    }
    
    
    public List<Appointment> listNurseAppointments(Nurse nurse)
    {
        return getEntityManager().createQuery("SELECT a FROM Appointment a INNER JOIN a.patient p WHERE a.department.id = ?1").setParameter(1, nurse.getDepartment().getId()).getResultList();
    }
    
    
}
