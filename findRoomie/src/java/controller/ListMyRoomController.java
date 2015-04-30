/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.UserFacade;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.Post;
import model.User;

/**
 *
 * @author Ashok Subedi
 */
@ManagedBean
@RequestScoped
public class ListMyRoomController {
        @EJB
    private UserFacade userFacade;
    private ExternalContext ec;

    /**
     * Creates a new instance of ListMyRoomController
     */
    List<Post> posts;
    public ListMyRoomController() {
        posts=new ArrayList<>();
        ec = FacesContext.getCurrentInstance().getExternalContext();
    }
    
    public String viewAll(){
     String email = ec.getRemoteUser();        
        System.out.println("email: " + email);        
        User user = userFacade.findByEmail(email);
        posts.addAll(user.getUserPosts());
        return "listMyRooms";
    }
    
   public List<Post> getPosts(){       
       return posts;
    }
   
   public String removeThisPost(Post myPost){
       posts.remove(myPost);
       return "listMyRooms";
   }
}
