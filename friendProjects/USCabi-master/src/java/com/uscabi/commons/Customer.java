/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.commons;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author noman-pc
 */
@Entity
public class Customer extends Person implements Serializable {

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;
    private static final long serialVersionUID = 1L;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String contactNumber, Date registrationDate, String email, String image, Date dob, Address address, UserCredential user) {
        super(firstName, lastName, contactNumber, registrationDate, email, image, dob, address, user);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

}
