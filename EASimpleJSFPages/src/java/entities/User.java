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
    @NamedQuery(name = "User.findByName",
        query = "SELECT u FROM User u WHERE u.name = :name")    
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    
    @OneToMany
    private List <Message> userMessageList; 

    public User() {
        this.userMessageList = new ArrayList<Message>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public List<Message> getUserMessageList() {
        return userMessageList;
    }

    public void setUserMessageList(List<Message> userMessageList) {
        this.userMessageList = userMessageList;
    }
    
    public void addMessageToList(Message newMessage){
        userMessageList.add(newMessage);
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
        return "entities.User my name is " + this.getName();
    }
    
}
