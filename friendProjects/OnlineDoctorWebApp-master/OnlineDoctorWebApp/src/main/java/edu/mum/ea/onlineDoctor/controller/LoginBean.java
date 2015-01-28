/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.controller;

import edu.mum.ea.onlineDoctor.entity.AppUser;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Stella
 */
@Named(value = "loginBean")
@SessionScoped 
public class LoginBean implements Serializable{
    
    @PersistenceContext
    private EntityManager em;
    private String username;
  private String password;
  private AppUser currentUser;
  private boolean isLoggedIn;
    /**
     * Creates a new instance of LoginBean2
     */
    public LoginBean() {
        
    }

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

    public AppUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(AppUser currentUser) {
        this.currentUser = currentUser;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
    
    
  public AppUser findAppUserBYuserName(String username) {
        Query query = em.createNamedQuery("findAppUserByuserName");
        query.setParameter("uname", username);
        if (!query.getResultList().isEmpty()) {
            AppUser logedinUser=(AppUser) query.getSingleResult();
            return logedinUser;
        }
        return null;
    }
  public String login () {
      System.out.println("in login bean11111111111111111111");
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) 
        context.getExternalContext().getRequest();
    try {
      request.login(this.username, this.password);
      this.isLoggedIn=true;
      this.currentUser=this.findAppUserBYuserName(username);
       HttpSession session = (HttpSession)
             FacesContext.getCurrentInstance().getCurrentInstance().getExternalContext().getSession(false);

             session.setAttribute(username, request);
           session.setAttribute("currentUser", currentUser);
        System.out.println("the logedin user is :" +currentUser.getFirstName()+","+currentUser.getLastName());
        AppUser user=(AppUser)session.getAttribute("currentUser");
    } catch (ServletException e) {
   
      context.addMessage(null, new FacesMessage("Invalid Username or Password."));
      return "login";
    }
    return "/home?faces-redirect=true";
  }


//  public void logout() {
//    FacesContext context = FacesContext.getCurrentInstance();
//    HttpServletRequest request = (HttpServletRequest) 
//        context.getExternalContext().getRequest();
//    try {
//      request.logout();
//    } catch (ServletException e) {
//   
//      context.addMessage(null, new FacesMessage("Logout failed."));
//    }
//  }
  
   public String logout() {
        HttpSession session = (HttpSession)
             FacesContext.getCurrentInstance().getCurrentInstance().getExternalContext().getSession(false);

             FacesContext context = FacesContext.getCurrentInstance();
           
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
       
        System.out.println(" user " + request.getUserPrincipal().getName());
        

        if (session != null) {

            session.invalidate();
        }
        return "/home?faces-redirect=true";

    }
}
    

