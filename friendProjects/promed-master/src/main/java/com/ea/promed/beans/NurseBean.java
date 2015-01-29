/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.beans;

import com.ea.promed.entities.Appointment;
import com.ea.promed.entities.Doctor;
import com.ea.promed.entities.Nurse;
import com.ea.promed.entities.Nurse;
import com.ea.promed.entities.User;
import com.ea.promed.facades.NurseFacade;
import com.ea.promed.facades.UserFacade;
import com.ea.promed.util.Email;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.MessagingException;

/**
 *
 * @author kunda_000
 */
@ManagedBean
@RequestScoped
public class NurseBean extends AbstractBean {

    @EJB
    private UserFacade userFacade;
    
    @EJB
    private NurseFacade nurseFacade;
    
    @EJB
    private Email emailBean;
    
    private Nurse nurse;
    
    
    public NurseBean() {
    }

    public Nurse getNurse() {
        
        if(nurse == null)
        {
            nurse = new Nurse();
            nurse.setUser(new User());
        }
        
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }
    
    
    public String createNurse() throws MessagingException, UnsupportedEncodingException
    {
        
        if(nurse.getId() != null)
        {
            Nurse eNurse = (Nurse) sessionMap.get("eNurse");
            
            nurse.setUser(eNurse.getUser());
            
            nurseFacade.edit(nurse);
            
            sessionMap.put("message", "Nurse Info updated Successfully.");
            
        }else{
        
        
            String code = UUID.randomUUID().toString();

            String hash = Hashing.sha256().hashString(code, Charsets.UTF_8).toString();

            nurse.getUser().setVerification(hash);
            
            nurse.getUser().setPassword(hash);

            nurseFacade.create(nurse);

            userFacade.activateUser(nurse.getUser(), 3);

            emailBean.setToemail(nurse.getUser().getEmail());
            emailBean.setSubject("Login Credentials : Pro Medical Services");
            emailBean.setMessagetext(nurse.getFirstName(),"Your user name is " +nurse.getUser().getUsername()+ ". You can create new password by clicking below link.",hash);

            emailBean.sendEMail();

            sessionMap.put("message", "Nurse Info added Successfully.");
            
        }
        
        
        return "nurses?faces-redirect=true";
    }
    
    
    
    public void editNurse(String nurseid) throws IOException
    {
        Long did = Long.parseLong(nurseid);
        nurse = nurseFacade.find(did);
        
        if(nurse != null)
        {
            sessionMap.put("eNurse", nurse);
        }
    }
    
    
    public List<Nurse> listAllNurses()
    {
        return nurseFacade.listAllNurses();
    }
    
    
    
    public List<Appointment> listNurseAppointments()
    {
        Nurse cNurse = (Nurse) sessionMap.get("cNurse");
        
        return nurseFacade.listNurseAppointments(cNurse);
        
    }
    
    
    
}
