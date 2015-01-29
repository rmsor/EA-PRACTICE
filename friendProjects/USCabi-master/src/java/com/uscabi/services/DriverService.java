/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.services;

import com.uscabi.clientservices.IDriverService;
import com.uscabi.commons.StatusLocation;
import com.uscabi.commons.UserCredential;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.transaction.Transactional;

/**
 *
 * @author noman-pc
 */
@ManagedBean
@SessionScoped
@Transactional
public class DriverService implements Serializable {

    @EJB
    private IDriverService driverDAO;
    
    private StatusLocation statusLocation;

    private UserCredential user;

    
    private String driverUserName;

    
    public DriverService() {
    }
    
     @PostConstruct
    public void init() {
        this.selectedIncludePath = "/views/operator/driver.xhtml";
        //this.operatorUserName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        //this.operatorUserName = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        //System.out.print(operatorUserName);
        this.user = (UserCredential) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("driverKey");
        
        this.driverUserName = user.getUsername();
        
        this.statusLocation=new StatusLocation();
    }

    public StatusLocation getStatusLocation() {
        return statusLocation;
    }

    public void setStatusLocation(StatusLocation statusLocation) {
        this.statusLocation = statusLocation;
    }


    private String selectedIncludePath;

    public String getSelectedIncludePath() {
        return selectedIncludePath;
    }

    public void setSelectedIncludePath(String selectedIncludePath) {
        this.selectedIncludePath = selectedIncludePath;
    }

    public String doLocationUpdate(){
        
        driverDAO.updateLocation(statusLocation, driverUserName);

        setSelectedIncludePath("/views/driver/locationUpdate.xhtml");

        return "/secure/driver/home.xhtml";
    }

    public void manageLocationUpdate(ActionEvent e) {
        
        setSelectedIncludePath("/views/driver/locationUpdate.xhtml");
    }

    public void manageBooking(ActionEvent e) {
        setSelectedIncludePath("/views/driver/accept.xhtml");
    }

    public void manageProfile(ActionEvent e) {
        setSelectedIncludePath("/views/driver/accept.xhtml");
    }
}
