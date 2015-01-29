/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.services;

import com.uscabi.commons.UserCredential;
import com.uscabi.commons.userType;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author noman-pc
 */
@ManagedBean
@SessionScoped
public class NavigationService implements Serializable {

    /**
     * Redirect to login page.
     *
     * @return Login page name.
     */
    public String redirectToLogin() {
        return "/default.xhtml?faces-redirect=true";
    }

    /**
     * Go to login page.
     *
     * @return Login page name.
     */
    public String toLogin() {
        return "/default.xhtml?faces-redirect=true";
    }

    /**
     * Redirect to info page.
     *
     * @return Info page name.
     */
    public String redirectToInfo() {
        return "/info.xhtml?faces-redirect=true";
    }

    /**
     * Go to info page.
     *
     * @return Info page name.
     */
    public String toInfo() {
        return "/info.xhtml";
    }

    /**
     * Redirect to welcome page.
     *
     * @return Welcome page name.
     */
    public String redirectToWelcome(UserCredential user) {
        if (user.getUsertype().equals(userType.ADMIN)) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminKey", user);       
            return "/secure/admin/home.xhtml?faces-redirect=true";

        } else if (user.getUsertype().equals(userType.CUSTOMER)) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("customerKey", user);       
            return "/secure/customer/home.xhtml?faces-redirect=true";

        } else if (user.getUsertype().equals(userType.DRIVER)) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("driverKey", user);       
            return "/secure/driver/home.xhtml?faces-redirect=true";

        } else if (user.getUsertype().equals(userType.OPERATOR)) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("operatorKey", user);       
            return "/secure/operator/home.xhtml?faces-redirect=true";

        }

        return "/secure/customer/home.xhtml?faces-redirect=true";
    }

    /**
     * Go to welcome page.
     *
     * @return Welcome page name.
     */
    public String toWelcome() {
        return "/secured/welcome.xhtml";
    }

}
