/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tourproject;

import entities.ProgrammedTour;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class ProgrammedTourFacade extends AbstractFacade<ProgrammedTour> {
    @PersistenceContext(unitName = "com.mycompany_TourProject_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgrammedTourFacade() {
        super(ProgrammedTour.class);
    }
    
}
