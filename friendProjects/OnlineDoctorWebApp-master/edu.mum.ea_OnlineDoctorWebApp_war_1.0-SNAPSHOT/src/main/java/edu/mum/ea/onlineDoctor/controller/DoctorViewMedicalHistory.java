/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.controller;

import edu.mum.ea.onlineDoctor.entity.MedicalRecord;
import edu.mum.ea.onlineDoctor.entity.Patient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Senai
 */
@Named
@RequestScoped
public class DoctorViewMedicalHistory {

    private Patient selectedPatient;
    private List<MedicalRecord> records;

    @PostConstruct
    public void init() {
        //PatientService.fillMedicalRecord(selectedPatient);
        selectedPatient = new Patient();
        selectedPatient.setFirstName("Senai");
        
        records=new ArrayList<>();
        MedicalRecord mr=new MedicalRecord();
        mr.setRecordDate(new Date());
        mr.setRemarks("This is it ");
        mr.setSymptoms("This wer Symptoms.");
        
        records.add(mr);
        selectedPatient.setMedicalRecord(records);

    }

    public Patient getSelectedPatient() {
        return selectedPatient;
    }

    public void setSelectedPatient(Patient selectedPatient) {
        this.selectedPatient = selectedPatient;
    }

    public List<MedicalRecord> getRecords() {
        return records;
    }

    public void setRecords(List<MedicalRecord> records) {
        this.records = records;
    }

}
