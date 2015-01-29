/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.beans;

import com.ea.promed.entities.Client;
import com.ea.promed.entities.Doctor;
import com.ea.promed.entities.Nurse;
import com.ea.promed.entities.User;
import com.ea.promed.facades.ClientFacade;
import com.ea.promed.facades.DoctorFacade;
import com.ea.promed.facades.NurseFacade;
import com.ea.promed.facades.UserFacade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.servlet.ServletException;

/**
 *
 * @author you
 */

@ManagedBean
@RequestScoped
public class AuthBackingBean extends AbstractBean {

    private static final Logger log = Logger.getLogger(AuthBackingBean.class.getName());
    
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private DoctorFacade doctorFacade;
    
    @EJB
    private NurseFacade nurseFacade;
    
    @EJB
    private ClientFacade clientFacade;
    

    public AuthBackingBean() {
        
    }
    

    public void logout() throws IOException {
        String result = "/dashboard/?logout";
        
        try {
            request.logout();
            
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
            //result = "/dashboard";
        }
            
        ec.redirect(ec.getRequestContextPath() + result);
    }
    
    
    public void userRoleRedirect() throws IOException{
        
        String username = request.getUserPrincipal().getName();
        User cUser = userFacade.getUserByUserName(username);
        sessionMap.put("cUser", cUser);
        
        if(request.isUserInRole("ADMIN"))
        {
            sessionMap.put("role", "ADMIN");
            ec.redirect(ec.getRequestContextPath() + "/dashboard/admin/");
            
        }else if(request.isUserInRole("DOCTOR")){
            
            sessionMap.put("role", "DOCTOR");
            sessionMap.put("cDoctor", doctorFacade.getDoctorByUser(cUser));
            ec.redirect(ec.getRequestContextPath() + "/dashboard/doctor/");
            
        }else if(request.isUserInRole("NURSE")){
            
            sessionMap.put("role", "NURSE");
            sessionMap.put("cNurse", nurseFacade.getNurseByUser(cUser));
            ec.redirect(ec.getRequestContextPath() + "/dashboard/nurse/");
            
        }else if(request.isUserInRole("USER")){
            
            sessionMap.put("role", "USER");
            sessionMap.put("cClient", clientFacade.getClientByUser(cUser));
            ec.redirect(ec.getRequestContextPath() + "/dashboard/user/");
            
        }
 
    }
    
    
    
    public void isAdmin() throws IOException{
        
        if(request.isUserInRole("ADMIN"))
        {
            
        }else{
            ec.redirect(ec.getRequestContextPath() + "/access-denied.xhtml");
        }
 
    }
    
    
    public void isDoctor() throws IOException{
        
        if(request.isUserInRole("DOCTOR"))
        {
            
        }else{
            ec.redirect(ec.getRequestContextPath() + "/access-denied.xhtml");
        }
 
    }
    
    
    public void isNurse() throws IOException{
        
        
        if(request.isUserInRole("NURSE"))
        {
            
        }else{
            ec.redirect(ec.getRequestContextPath() + "/access-denied.xhtml");
        }
 
    }
    
    
    public void isUser() throws IOException{
        
        
        if(request.isUserInRole("USER"))
        {
            
        }else{
            ec.redirect(ec.getRequestContextPath() + "/access-denied.xhtml");
        }
 
    }
    
    
}