/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.facades;

import com.ea.promed.entities.Patient;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author kunda_000
 */
@Stateless
public class PatientFacade extends AbstractFacade<Patient> {
    
    @PersistenceContext(unitName = "promedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PatientFacade() {
        super(Patient.class);
    }
    
    public List<Patient> listAllPatients()
    {
        return getEntityManager().createQuery("SELECT p FROM Patient p ORDER BY p.id DESC").getResultList();
    }
    
}
