/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.service;

import edu.mum.ea.onlineDoctor.entity.Appointment;
import edu.mum.ea.onlineDoctor.entity.MedicalRecord;
import edu.mum.ea.onlineDoctor.entity.Patient;
import edu.mum.ea.onlineDoctor.facade.MedicalRecordFacade;
import edu.mum.ea.onlineDoctor.serviceI.MedicalRecordingServiceBeanLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Senai
 */
@Stateless
public class MedicalRecordingServiceBean implements MedicalRecordingServiceBeanLocal {

    @Inject
    private MedicalRecordFacade medicalrecordFacade;

    private final Logger log = Logger.getLogger(getClass().getName());

    @Override
    public boolean addMedicalRecord(MedicalRecord newMedicalRecord) {
        return true;
    }

    @Override
    public List<MedicalRecord> getAllMedicalRecords(Patient patient) {
        return new ArrayList();
    }

    /**
     *
     * @param appointment
     * @param sessionchatMessages
     * @return
     */
    @Override
    public boolean recordchatMessages(Appointment appointment, String sessionchatMessages) {
        try {
            MedicalRecord medicalRecord = medicalrecordFacade.getMedicalRecordByAppointmentId(appointment.getId());
            if (medicalRecord == null) {
                medicalRecord = new MedicalRecord();
                medicalRecord.setAppointment(appointment);
                medicalRecord.setChatMessages(sessionchatMessages);
                medicalrecordFacade.create(medicalRecord);
                return true;
            } else {
                medicalRecord.setChatMessages(sessionchatMessages);
                medicalrecordFacade.edit(medicalRecord);
                return true;
            }
        } catch (Exception e) {
            log.info("Error: MedicalRecordingServiceBean could not record the chatMessages due to persistance error.");
            return false;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
