/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.onlineDoctor.serviceI;

import edu.mum.ea.onlineDoctor.entity.Patient;
import javax.ejb.Local;

/**
 *
 * @author Fetiya
 */
@Local
public interface PatientServiceBeanLocal {
  
    public String signupPatient(Patient newPatient);
    public Patient getPatientById(Long id);
}
