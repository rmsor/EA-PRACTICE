/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * 
 * @author Steve
 */
@EntityListeners({NameValidationListener.class})
@Entity
@Table (name = "muser")
//derby reserves the name User -- so we rename to MUSER
@NamedQueries({
    //notice since we are using JPQL we select from User (not MUSER)
    @NamedQuery(name = "User.findByEmail",
        query = "SELECT u FROM User u WHERE u.emailAddress = :emailAddress")    
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Integer version;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String password;
    
    @OneToMany
    private List <Message> rcvMessageList; 
    
    @OneToMany
    private List <Message> sentMessageList; 

    public User() {
        this.rcvMessageList = new ArrayList<>();
        this.sentMessageList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public List<Message> getRcvMessageList() {
        return rcvMessageList;
    }

    public void setRcvMessageList(List<Message> userMessageList) {
        this.rcvMessageList = rcvMessageList;
    }
    
    public void addMessageToRcvList(Message newMessage){
        rcvMessageList.add(newMessage);
    }
    public void addMessageToSentList(Message newMessage){
        sentMessageList.add(newMessage);
    }

    public List<Message> getSentMessageList() {
        return sentMessageList;
    }

    public void setSentMessageList(List<Message> sentMessageList) {
        this.sentMessageList = sentMessageList;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.User my name is " + this.getEmailAddress();
    }
    
}
