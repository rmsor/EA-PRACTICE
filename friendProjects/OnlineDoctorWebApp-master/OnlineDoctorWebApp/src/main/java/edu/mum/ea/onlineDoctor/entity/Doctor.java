/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.onlineDoctor.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


/**
 *
 * @author HailelulLakew
 */
@Entity
public class Doctor extends AppUser implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /*the join table between Doctor and Specialization is renamed to JND_DOCT_SPEC 
     as well as each join column (thanks to the @JoinTable annotation).
     The joinColumns element refers to the owning side (the Doctor)
     and the inverseJoinColumns refers to the inverse owning
     side (the Specialization)*/
    @ManyToMany
    @JoinTable(name = "DoctorSpecializations",joinColumns = @JoinColumn(name = "doctor_ID"),
    inverseJoinColumns = @JoinColumn(name = "specialization_ID"))  
    private List<Specialization> specializations;  
     
    @OneToMany(mappedBy="doctor")
   private List<MedicalRecord> medicalRecords;
    
    @OneToMany(mappedBy = "doctor") //"doctorInAppointment")
    private List<Appointment> appointments;
    
  
    /**
     * @return the medicalRecords
     */
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    /**
     * @param medicalRecords the medicalRecords to set
     */
    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }
    
    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.specializations);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Doctor other = (Doctor) obj;
        if (!Objects.equals(this.specializations, other.specializations)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Doctor{" + "specializations=" + specializations + '}';
    }

}
