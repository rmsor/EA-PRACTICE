/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.MessageFacade;
import boundary.UserFacade;
import entities.Message;
import entities.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Steve
 */
@Named(value = "MessageView")
@SessionScoped
public class MessageView implements Serializable
{
    @Inject
    private MessageFacade myMessageFacade;
    private Message myMessage;
    @Inject
    private UserFacade myUserFacade;
    private User myUser;

    
    @PostConstruct
    public void createMyMessage(){
        this.myMessage = new Message();
        this.myUser = new User();
        FacesMessage facesMessage;
        facesMessage = new FacesMessage("MessageView Postconstruct Complete");
       facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        
    }

    public User getMyUser() {
        return myUser;
    }

    public void setMyUser(User myUser) {
        this.myUser = myUser;
    }

    public MessageFacade getMyMessageFacade() {
        return myMessageFacade;
    }

    public void setMyMessageFacade(MessageFacade myMessageFacade) {
        this.myMessageFacade = myMessageFacade;
    }

    public Message getMyMessage() {
        return myMessage;
    }
    
    public List<Message> getAllMessages() {
        return myMessageFacade.findAll();
    }

    public void setMyMessage(Message myMessage) {
        this.myMessage = myMessage;
    }
    
    //return total number of messages
    public int getNumberOfMessages(){
        return myMessageFacade.findAll().size();
    }
    //save the message and return "theend"
    public String postMessage(){
        System.out.println("MessageView about to save the message to DB");
        FacesMessage facesMessage = new FacesMessage("MessageView saves message to DB");
       facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
       this.myMessageFacade.create(myMessage);
      
      System.out.println("MessageView about to save the user to DB");
      myUser.addMessageToList(myMessage);
      myUserFacade.create(myUser);
      return "theend";
      // return "lastpage";
    }
    public String sendNewMessage(){
        this.myMessage = new Message();
        FacesMessage facesMessage = new FacesMessage("MessageView sendNewMessage");
       facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
       return "index";
        
    }
    /**
     * Creates a new instance of MessageView
     */
    public MessageView() {
        System.out.println("MessageView constructor called");
        
    }
    
}
