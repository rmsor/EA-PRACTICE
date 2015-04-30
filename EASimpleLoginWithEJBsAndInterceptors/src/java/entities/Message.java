/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Steve
 */
@Entity
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String myTextMessage;
    @ManyToOne
    private User mySender;
    @ManyToOne
    private User myReceiver;

    public String getMyTextMessage() {
        return myTextMessage;
    }

    public void setMyTextMessage(String textMessage) {
        this.myTextMessage = textMessage;
        //FacesMessage facesMessage = new FacesMessage("Message text set to " + myTextMessage);
        //facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        //FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Message my text message is " + this.getMyTextMessage();
    }
    
}
