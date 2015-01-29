/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.beans;

import com.ea.promed.entities.Appointment;
import com.ea.promed.entities.Client;
import com.ea.promed.entities.Doctor;
import com.ea.promed.entities.Nurse;
import com.ea.promed.entities.Status;
import com.ea.promed.facades.AppointmentFacade;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.MessagingException;

/**
 *
 * @author kunda_000
 */
@ManagedBean
@SessionScoped
public class AppointmentBean extends AbstractBean {

    @EJB
    AppointmentFacade appointmentFacade;
    
    private Appointment appointment;
    
    
    public AppointmentBean() {
    }

    public Appointment getAppointment() {
        if(appointment == null)
        {
            appointment = new Appointment();
        }
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    
    
    public String createAppointment() 
    {
        
        if(appointment.getId() != null)
        {
            
            appointmentFacade.edit(appointment);
            
//            sessionMap.put("message", "Appointment Info updated Successfully.");
            
        }else{
        
            appointmentFacade.create(appointment);

//            sessionMap.put("message", "Appointment Info added Successfully.");
            
        }
        
        
        return "appointments?faces-redirect=true";
    }
    
    
    
    public String editAppointment(Appointment appointment) 
    {
        setAppointment(appointment);
        
        return "appointment";
    }
    
    
    public Status[] getStatuses()
    {
        return Status.values();
    }
    
    
    public List<Appointment> listAllAppointments()
    {
        return appointmentFacade.listAllAppointments();
    }
    
    
    public void editByDoctor(String appointmentId) throws IOException
    {
        Long aid = Long.parseLong(appointmentId);
        Appointment tempAppointment = appointmentFacade.find(aid);
        
        Doctor cDoctor = (Doctor) sessionMap.get("cDoctor");
        
        if(Objects.equals(tempAppointment.getDoctor().getId(), cDoctor.getId()))
        {
            setAppointment(tempAppointment);
        }else{
            ec.redirect("/403.xhtml");
        }
               
    }
    
    
    public void editByNurse(String appointmentId) throws IOException
    {
        Long aid = Long.parseLong(appointmentId);
        Appointment tempAppointment = appointmentFacade.find(aid);
        
        Nurse cNurse = (Nurse) sessionMap.get("cNurse");
        
        if(Objects.equals(tempAppointment.getDepartment().getId(), cNurse.getDepartment().getId()))
        {
            appointment = tempAppointment;
        }else{
            ec.redirect("/403.xhtml");
        }
               
    }
    
    
    public String addPresecription(Appointment appointment)
    {
        setAppointment(appointment);
        
        return "priscription";
    }
    
    public String updatePrescription()
    {
        
        
        appointmentFacade.edit(appointment);
        
        return "appointments?faces-redirect=true";
    }
    
    
    public String addAppointment()
    {
        setAppointment(new Appointment());
        
        return "appointment";
    }
    
    
    
}
