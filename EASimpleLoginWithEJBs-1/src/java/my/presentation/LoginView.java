/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import boundary.UserFacade;
import entities.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Steve
 */
@Named(value = "loginView")
@SessionScoped
public class LoginView implements Serializable {
    
    private String emailAddr;
    private String password;
    private User myUser;
    @Inject
    private UserFacade myUserFacade;

    /**
     * Creates a new instance of LoginView
     */
    public LoginView() {
    }
    @PostConstruct
    public void showPostConstructMessage(){
        FacesMessage facesMessage;
        facesMessage = new FacesMessage("RegistrationView Postconstruct Complete");
       facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    
    public String loginUser() {
        //get the user from the DB and verify that the password is correct
        // if correct route to the sendMessage page
        //otherwise send a failed login message and return to login
        myUser = myUserFacade.findUserByEmail(emailAddr);
        if (myUser == null) {
            //no user -- this login fails
            FacesMessage facesMessage = new FacesMessage("Login Failed - no user for that email address");
            facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            System.out.println("Login failed no user for email address: " + emailAddr);
            return "index";
        } else {
            //found a user -- check the password
            String userPassword = myUser.getPassword();
            if (userPassword.equals(this.password)) {
                //login successful
                return "sendMessage";
            } else {
                //password incorrect print a message and return to login page
                FacesMessage facesMessage = new FacesMessage("Login Failed - password incorrect");
                facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                System.out.println("Login failed password entered =  " + this.password + " password in DB = " + userPassword);
                return "index";

            }
                 
      }
      
        
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
