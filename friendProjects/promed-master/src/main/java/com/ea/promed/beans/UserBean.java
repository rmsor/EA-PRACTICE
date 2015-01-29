/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ea.promed.beans;

import com.ea.promed.entities.Client;
import com.ea.promed.entities.Patient;
import com.ea.promed.entities.User;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.ea.promed.facades.UserFacade;
import com.ea.promed.util.Email;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.mail.MessagingException;


@ManagedBean
@RequestScoped
public class UserBean extends AbstractBean
{
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private Email emailBean;
    
    User user;
    
    String verifyPassword;
    
    
    
    public UserBean()
    {
        user = new User();
        
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
    
    
    
    
    
    
    public String verifyUser(String code) throws IOException
    {
        User checkUser = userFacade.getUserByCode(code);
        
        if(checkUser != null)
        {
            checkUser.setVerification("");
            
            userFacade.activateUser(checkUser,4);
            userFacade.edit(checkUser);
            
            sessionMap.put("message", "User verified Successfully. Please login with your credentials.");
            
            ec.redirect("/dashboard/");
            
        }else{
            sessionMap.put("message", "Verification Code Error.");
        }
        
        return "/verification";
    }
    

    public String forgetPassword()
    {
        
        user = userFacade.getUserByEmail(user.getEmail());
        if(user != null)
        {
            String code = UUID.randomUUID().toString();
            user.setVerification(code);


            userFacade.edit(user);

            emailBean.setToemail(user.getEmail());
            emailBean.setSubject("Password Reset Request : Pro Medical Services");
            emailBean.setMessagetext("Dear User,","Please click below link to reset password.",code);

            emailBean.sendEMail();
            
            context.addMessage("forget:email", new FacesMessage("Email has been sent to your email address."));
            
        }else{
            context.addMessage("forget:email", new FacesMessage("User not found with this email."));
        }
        return "forget-password";
    }
    
    
    
    public boolean checkVerification(String code)
    {
        User cUser = userFacade.getUserByCode(code);
        
        if(cUser != null)
        {
            sessionMap.put("cUser", cUser);
            return true;
        }else{
            sessionMap.put("cUser", null);
            return false;
        }
        
    }
    
    
    public String resetPassword() throws IOException
    {
        
        User cUser = (User) sessionMap.get("cUser");
        String code = cUser.getVerification();
        
        if(user.getPassword().equals(getVerifyPassword()))
        {
            
            String hash = Hashing.sha256().hashString(user.getPassword(), Charsets.UTF_8).toString();

            cUser.setPassword(hash);
            cUser.setVerification("");

            userFacade.edit(cUser);

            sessionMap.put("message", "Password Updated successfully. Please login with your new username and password.");

            System.out.println("password changed");
            
            return "/dashboard/index?faces-redirect=true";
            
        }else{
            sessionMap.put("message", "Password Not Matched.");
        }
        
        return "reset-password?faces-redirect=true&code="+code;
        
    }
    
    
    
    
    public void clearMessage(PhaseEvent event) {
    if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
        FacesContext.getCurrentInstance().getExternalContext()
            .getSessionMap().remove("message");
    }
}
    
}
