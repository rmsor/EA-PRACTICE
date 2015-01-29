/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.clientservices;

import com.uscabi.commons.Booking;
import com.uscabi.commons.Driver;
import com.uscabi.commons.StatusLocation;
import javax.ejb.Remote;

/**
 *
 * @author noman-pc
 */
public interface IDriverService {
    
    
    public void addDriver(Driver driver);

    public void updateDriver(Driver driver);

    public void disableDriver(Driver driver);    
    
    public void acceptBooking(Driver driver, Booking booking);

    public void rejectBooking(Driver driver, Booking booking);

    public void updateLocation(StatusLocation statusLocation, String driverUserName);
    
}
