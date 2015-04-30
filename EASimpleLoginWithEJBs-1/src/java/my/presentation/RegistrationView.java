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
@Named(value = "RegistrationView")
@SessionScoped
public class RegistrationView implements Serializable {
    @Inject
    private UserFacade myUserFacade;
    private User myUser;
    private String emailAddress;
    private String firstName;
    private String lastName;

    /**
     * Creates a new instance of RegistrationView
     */
    public RegistrationView() {
        System.out.println("RegistrationView constructor called");
    }
    @PostConstruct
    public void showPostConstructMessage(){
        FacesMessage facesMessage;
        facesMessage = new FacesMessage("RegistrationView Postconstruct Complete");
       facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    public String saveRegInfo() {
        myUser = myUserFacade.findUserByEmail(emailAddress);
        if (myUser == null){
          //this is  new user -- do a create
          System.out.println("Registration Managed Bean creating new user ");
          myUser = new User();
          myUser.setEmailAddress(emailAddress);
          myUser.setFirstName(firstName);
          myUser.setLastName(lastName);
          myUser.setPassword(emailAddress);
          myUserFacade.create(myUser);
          return "login";
      }
      else {
          //this user already exists so they can not register again
            FacesMessage facesMessage = new FacesMessage("Registration Failed - user already exists");
            facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            System.out.println("Registration failed - user exists: " + myUser.toString());
            return "index";
      }
      
      // return "lastpage";
    }

    public UserFacade getMyUserFacade() {
        return myUserFacade;
    }

    public void setMyUserFacade(UserFacade myUserFacade) {
        this.myUserFacade = myUserFacade;
    }

    public User getMyUser() {
        return myUser;
    }

    public void setMyUser(User myUser) {
        this.myUser = myUser;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
}
