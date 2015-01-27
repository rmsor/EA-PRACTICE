/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.MessageFacade;
import entities.Message;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author Steve
 */
@Named(value = "MessageView")
@SessionScoped
public class MessageView implements Serializable
{
    @Resource(mappedName = "jms/myQueue")
    private Queue myQueue;
    @Inject
    @JMSConnectionFactory("jms/myQueueConnectionFactory")
    private JMSContext context;
    @Inject
    private MessageFacade myMessageFacade;
    private Message myMessage;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @PostConstruct
    public void createMyMessage(){
        this.myMessage = new Message();
        FacesMessage facesMessage = new FacesMessage("MessageView Postconstruct");
       facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        
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
      sendJMSMessageToMyQueue(myMessage.getMyTextMessage());
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

    private void sendJMSMessageToMyQueue(String messageData) {
        context.createProducer().send(myQueue, messageData);
    }
    
}
