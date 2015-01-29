/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.commons;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author noman-pc
 */
@Entity
public class Admin extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    public Admin() {
    }

    public Admin(String firstName, String lastName, String contactNumber, Date registrationNumber, String email, String image, Date dob, Address address, UserCredential user) {
        super(firstName, lastName, contactNumber, registrationNumber, email, image, dob, address, user);
    }

}
