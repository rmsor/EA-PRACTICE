/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.clientservices;

import com.uscabi.commons.Booking;
import com.uscabi.commons.Car;
import com.uscabi.commons.Customer;
import com.uscabi.commons.StatusLocation;
import java.util.List;

/**
 *
 * @author noman-pc
 */
public interface ICustomerService {

    public Customer addCustomer(Customer customer, String image);
    
    public Customer addCustomer(Customer customer);

    public void updateCustomer(Customer customer);

    public void disableCustomer(Customer customer);

    public void sendFeedback(Customer customer, Car car);

    public Booking addBooking(Booking booking, Car car, String customerUserName);

    public void payFare(Customer customer, Car car);

    public Car findCar(long id);

    public void cancelBooking(Customer customer, Car car);

    public void updateLocation(Customer customer);

    public List<StatusLocation> findAllDriverStatusLocation();

}
