/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utopia.beans;

import edu.utopia.entities.Admin;
import static edu.utopia.entities.EncryptPassword.getEncryptedPassword;
import edu.utopia.model.AdminEJB;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author fjoseph1313
 */
@Named(value = "AdminBean")
@RequestScoped
public class AdminBean {

    @EJB
    private AdminEJB adminEJB;
    private Admin admin;
   private String successMessage;
    public AdminBean() {
        this.admin = new Admin();
    }

    public Admin getAdmin() {
        return admin;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String registerAdmin() {
        //admin.setUserName(admin.getEmailAddress()); //assign username as email address but optional
        if (admin.getUserName().equals("")) {
            admin.setUserName(admin.getEmailAddress());
        }
        admin.setPassword(getEncryptedPassword(admin.getPassword()));
        Admin addedAdmin = this.adminEJB.createAdmin(admin);
        this.setSuccessMessage("Employee Register Sucessafully");
        this.admin = null;
        return "registerEmployee";
    }

    public Admin searchAdminByUserName(String admin) {
        return this.adminEJB.findAdmin(admin);
    }
    
  
}
