package controller;

import ejb.EmailSessionBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author xtrememe
 */
@ManagedBean
@RequestScoped
public class EmailController extends BaseController{
    @EJB
    private EmailSessionBean emailSessionBean;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    /**
     * Creates a new instance of EmailController
     */
    public EmailController() {
    }
    
    
    public String sendEmail() throws IOException{
        
        if(emailSessionBean.sendEmail(email)){

            getFlash().putNow("msg", "Please login using the password mailed to you");
            getFlash().putNow("class", "success");
            
        }else{
            getFlash().putNow("msg", "An error occured while resetting your password. Please try again later.");
            getFlash().putNow("class", "error");
        }
        
        getEc().redirect(getEc().getRequestContextPath() + "/faces/login.xhtml");
        
        return "";
    }
    
    public void forgotPassword() throws IOException{
         
        getEc().redirect(getEc().getRequestContextPath() + "/faces/forgotPassword.xhtml");
    
    }
}
