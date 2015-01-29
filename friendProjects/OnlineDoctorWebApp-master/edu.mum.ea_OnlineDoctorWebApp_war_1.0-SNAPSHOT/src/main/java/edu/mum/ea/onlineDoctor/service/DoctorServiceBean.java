/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.service;

import edu.mum.ea.onlineDoctor.entity.Doctor;
import edu.mum.ea.onlineDoctor.facade.DoctorFacade;
import edu.mum.ea.onlineDoctor.serviceI.DoctorServiceBeanLocal;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Senai
 */
@Stateless
public class DoctorServiceBean implements DoctorServiceBeanLocal {

    @Inject
    private DoctorFacade doctorFacade;

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorFacade.find(id);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
