/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.clientservices;

import com.uscabi.commons.Admin;
import com.uscabi.commons.Booking;
import com.uscabi.commons.Car;
import com.uscabi.commons.Customer;
import com.uscabi.commons.Driver;
import com.uscabi.commons.Operator;
import com.uscabi.commons.Payment;
import com.uscabi.commons.UserCredential;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author noman-pc
 */
public interface IAdminService {

    public Admin addAdmin(Admin admin);
    
    public Operator addOperator(Operator operator, String image);

    public void updateOperator(Operator operator);

    public void disableOperator(Operator operator);
    

    public Customer addCustomer(Customer customer, String image);

    public void updateCustomer(Customer customer);

    public void disableCustomer(Customer customer);
    

    public Car addCar(Car car, Driver driver);

    public void updateCar(Car car);

    public void disableCar(Car car);
    

    public Driver addDriver(Driver driver, Operator operator, String image);

    public void updateDriver(Driver driver);

    public void disableDriver(Driver driver);

    
    public Operator findOperator(long id);

    public Customer findCustomer(long id);

    public Car findCar(long id);

    public Driver findDriver(long id);

    public Booking findBooking(long id);

    public Payment findPayment(long id);


    public List<Customer> findCustomers();

    public List<Operator> findOperators();

    public List<Car> findCars();

    public List<Driver> findDrivers();

    public List<Booking> findBookings();

    public List<Payment> findPayments();
    

    public Customer findCustomer(String username);

    public Operator findOperator(String username);

    public Admin findAdmin(String username);
    
    public Driver findDriver(String username);

    public UserCredential findUser(String username);
    
}
