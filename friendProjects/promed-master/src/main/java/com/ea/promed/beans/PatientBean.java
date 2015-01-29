/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.beans;

import com.ea.promed.entities.Client;
import com.ea.promed.entities.Patient;
import com.ea.promed.facades.ClientFacade;
import com.ea.promed.facades.PatientFacade;
import com.ea.promed.facades.UserFacade;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kunda_000
 */

@ManagedBean
@RequestScoped
public class PatientBean extends AbstractBean {
    
    @EJB
    PatientFacade patientFacade;
    
    @EJB
    ClientFacade clientFacade;
    
    @EJB
    UserFacade userFacade;

    private Patient patient;
    
    public PatientBean() {
        
    }
    
    public Patient getSelected() {
        if (patient == null) {
            patient = new Patient();
        }
        return patient;
    }

    
    
    public void createPatient() throws IOException
    {
        
            Client client = (Client) sessionMap.get("cClient");
            
            patient.setClient(client);
            
            
            if(patient.getId() != null)
            {
                if(sessionMap.get("editID") == patient.getId())
                    patientFacade.edit(patient);
                else
                    ec.redirect("/403.xhtml");
            }else{
                patientFacade.create(patient);
            }
            
            
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
            
        sessionMap.put("message", "Patient updated successfully.");
        
        ec.redirect("patients.xhtml");
    }
    
    
    
    
    
    public void editPatient(String patientid) throws IOException
    {
        Client cClient = (Client) sessionMap.get("cClient");
        Long pid = Long.parseLong(patientid);
        patient = patientFacade.find(pid);
        if( !Objects.equals(patient.getClient().getId(), cClient.getId()))
        {
            ec.redirect("/403.xhtml");
        }else{
            sessionMap.put("editID", pid);
        }
    }
    
    
    
    public List<Patient> listAllPatients()
    {
        return patientFacade.findAll();
    }
    
    
    
}
