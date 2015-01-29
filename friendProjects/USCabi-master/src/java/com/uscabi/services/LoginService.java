/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.services;

/**
 *
 * @author noman-pc
 */
import com.uscabi.clientservices.ILoginService;
import com.uscabi.commons.UserCredential;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
@Transactional
public class LoginService implements Serializable {

    @EJB
    private ILoginService loginDAO;

    //private UserCredential user;
    private String username;
    private String password;

    private boolean loggedIn;

    @ManagedProperty(value = "#{navigationService}")
    private NavigationService navigationService;

    public LoginService() {
    }

    @PostConstruct
    public void init() {

    }

    public String doLogin() {
        RequestContext context = RequestContext.getCurrentInstance();
        loggedIn = false;
        UserCredential user = loginDAO.findUser(username, password);

        if (user != null) {
            loggedIn = true;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username));

            return navigationService.redirectToWelcome(user);

        } else {
            // Set login ERROR
            loggedIn = false;
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials"));


        }
       // FacesContext.getCurrentInstance().addMessage(null, message);
                context.addCallbackParam("loggedIn", loggedIn);


        // To to login page
        return navigationService.toLogin();
    }

    /**
     * Logout operation.
     *
     * @return
     */
    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;

        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return navigationService.toLogin();
    }

    //getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public NavigationService getNavigationService() {
        return navigationService;
    }

    public void setNavigationService(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

}
