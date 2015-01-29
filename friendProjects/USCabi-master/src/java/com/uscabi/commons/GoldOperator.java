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
public class GoldOperator extends Operator implements Serializable {
    private static final long serialVersionUID = 1L;

    public GoldOperator() {
    }

    public GoldOperator(String companyName, String firstName, String lastName, String contactNumber, Date registrationNumber, String email, byte[] image, Date dob, Address address, UserCredential user) {
        super(companyName, firstName, lastName, contactNumber, registrationNumber, email, image, dob, address, user);
    }
    
}
