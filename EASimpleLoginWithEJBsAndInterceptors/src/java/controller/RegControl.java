/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import Interceptors.LoggingInterceptor;
import boundary.UserFacade;
import commonEnums.ConstantInts;
import entities.User;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import my.presentation.RegistrationView;

/**
 *@Interceptors(LoggingInterceptor.class)
 * @author Steve
 */
@Stateless
@Interceptors(LoggingInterceptor.class)  //will do a post construct sysout
                                         //as well as an around invoke sysout
public class RegControl {
    @Inject
    private UserFacade myUserFacade;
    private User myUser;
    
    
    
    @PostConstruct
    public void showPostConstructMessage(){
        FacesMessage facesMessage;
        facesMessage = new FacesMessage("RegControl EJB Postconstruct Complete");
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    //@Interceptors(LoggingInterceptor.class)  use this if you only want one method intercepted
    public ConstantInts saveRegInfo(RegistrationView regView) {
        myUser = myUserFacade.findUserByEmail(regView.getEmailAddress());
        if (myUser == null) {
            //this is  new user -- do a create
            System.out.println("RegControl EJB creating new user ");
            myUser = new User();
            myUser.setEmailAddress(regView.getEmailAddress());
            myUser.setFirstName(regView.getFirstName());
            myUser.setLastName(regView.getLastName());
            myUser.setPassword(PasswordGenerator.getPassword(ConstantInts.PasswordLength.getValue()));
          //now use an asynchronous method to send email with the password

            myUserFacade.create(myUser);
            return ConstantInts.RegSuccess;
        } else {
            //this user already exists so they can not register again
            FacesMessage facesMessage = new FacesMessage("Registration Failed - user already exists");
            facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            System.out.println("Registration failed - user exists: " + myUser.toString());
            return ConstantInts.DupEmail;
        }
    }
}
