package controller;

import ejb.PostFacade;
import ejb.UserFacade;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.Post;
import model.User;

/**
 *
 * @author xtrememe
 */
@ManagedBean
@SessionScoped
public class LoginController extends BaseController{
    @EJB
    private PostFacade posts;
    @EJB
    private UserFacade userFacade;
    private User user;
    private String email;
    private List<Post> allPosts;

    
    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public String dashboard(){
    
        return "";
    }
    
    
    
    
    public String register(){
        try {
            getEc().redirect(getEc().getRequestContextPath() + "/faces/pages/user/signup.xhtml");
        } catch (IOException ex) {

        }
        
        return "";
    }
    
    public String displayName(){
        String email = getEc().getRemoteUser();
        
        user = userFacade.findByEmail(email);
        
        return user.getFirstName();
    }
    
    
    public String roomDetails(){
        System.out.println("inside room details");
        return "roomDetails?faces-redirect=true";
    }
    
    
    public String forgotPassword(){
        
        try {
            getEc().redirect(getEc().getRequestContextPath() + "/faces/forgotPassword.xhtml");
        } catch (IOException ex) {

        }
        
        return "";
    }
    
    public void logout() throws IOException {
        user = null;
        
        HttpServletRequest hsr = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        hsr.getSession().invalidate();
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/faces/" + "login.xhtml");
        
        
//        String result="/index?faces-redirect=true";
     
//    FacesContext context = FacesContext.getCurrentInstance();
//    HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
//     
//    try {
//      request.logout();
//    } catch (ServletException e) {
//      log.log(Level.SEVERE, "Failed to logout user!", e);
//      result = "/loginError?faces-redirect=true";
//    }
//     
//    return result;

    }
    
    
}

// http://www.pramati.com/docstore/1270002/index.htm
// http://www.avajava.com/tutorials/lessons/how-do-i-create-a-login-module.html
// http://stackoverflow.com/questions/20396276/jaas-exception-null-username-and-password
