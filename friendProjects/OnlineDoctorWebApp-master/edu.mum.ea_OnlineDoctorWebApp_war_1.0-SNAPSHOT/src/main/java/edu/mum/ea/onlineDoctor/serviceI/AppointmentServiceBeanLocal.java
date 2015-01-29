/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.onlineDoctor.serviceI;

import edu.mum.ea.onlineDoctor.entity.Appointment;
import edu.mum.ea.onlineDoctor.entity.Doctor;
import edu.mum.ea.onlineDoctor.entity.Patient;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Fetiya
 */
@Local
public interface AppointmentServiceBeanLocal {

    public List<Appointment> findAllAppointments();
    
    public String createAppointment(Appointment appointment) ;
    
    public List<Appointment> findPatientAppointments(Patient patient);
    
    public List<Appointment> findDoctorAppointments(Doctor doctor);
    
    public Appointment findAppointmentByID(Long id);

}
