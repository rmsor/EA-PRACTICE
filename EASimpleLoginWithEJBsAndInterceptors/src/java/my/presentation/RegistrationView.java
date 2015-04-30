/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.presentation;

import commonEnums.ConstantInts;
import controller.RegControl;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
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
    
    private String emailAddress;
    private String firstName;
    private String lastName;
    @Inject
    private RegControl myRegControl;

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
        //cal the RegControl EJB to do the registration check
        //the password generation, and the email send with password
        if (myRegControl.saveRegInfo(this) == ConstantInts.RegSuccess){
          return "login";
      }
      else {
          //this user already exists so they can not register again
            
            return "index";
      }
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
