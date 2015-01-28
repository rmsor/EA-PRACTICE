/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.serviceI;

import edu.mum.ea.onlineDoctor.entity.Appointment;
import edu.mum.ea.onlineDoctor.entity.MedicalRecord;
import edu.mum.ea.onlineDoctor.entity.Patient;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Senai
 */
@Local
public interface MedicalRecordingServiceBeanLocal {
    
    public boolean addMedicalRecord(MedicalRecord newMedicalRecord);
    
    public List<MedicalRecord> getAllMedicalRecords(Patient patient);
    
    public boolean recordchatMessages(Appointment appointment,String sessionchatMessages);
    
}
