/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.xml.bind.annotation.XmlRootElement;
import listener.AdminAccounntValidation;

/**
 *
 * @author atan
 */
@Entity
@EntityListeners({AdminAccounntValidation.class})
@XmlRootElement
public class AdminAccount extends UserAccount implements Serializable {

    public AdminAccount() {
    }

}
