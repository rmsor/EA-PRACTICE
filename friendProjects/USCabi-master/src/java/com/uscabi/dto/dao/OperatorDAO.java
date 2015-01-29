/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.dao;

import com.uscabi.clientservices.IOperatorService;
import com.uscabi.commons.Booking;
import com.uscabi.commons.Car;
import com.uscabi.commons.Driver;
import com.uscabi.commons.Operator;
import com.uscabi.commons.Payment;
import com.uscabi.commons.userType;
import com.uscabi.dto.idao.GenericPersistenceDAO;
import com.uscabi.dto.idao.IBookingDAO;
import com.uscabi.dto.idao.ICarDAO;
import com.uscabi.dto.idao.IDriverDAO;
import com.uscabi.dto.idao.IOperatorDAO;
import com.uscabi.dto.idao.IPaymentDAO;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author noman-pc
 */
@Stateless
public class OperatorDAO extends GenericPersistenceDAO<Operator, Long> implements IOperatorDAO, IOperatorService {
    
    @PersistenceContext(unitName = "USCabiPU")
    private EntityManager em;
    
    @EJB
    private IOperatorDAO operatorDAO;
    
    @EJB
    private IDriverDAO driverDAO;
    
    @EJB
    private ICarDAO carDAO;
    
    @EJB
    private IBookingDAO bookingDAO;
      
    @EJB
    private IPaymentDAO paymentDAO;
    
    @EJB
    private EmailSender emailSender;
    
    private String driverCurrentStatus;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public OperatorDAO() {
        super(Operator.class);
    }
//    
//    @Schedule(minute = "*/15")
//    public void driverStatusNotification() {
//        List<Driver> allDriver = driverDAO.findAll();
//        Iterator<Driver> itr = allDriver.iterator();
//        while (itr.hasNext()) {
//            Driver driver = itr.next();
//            driverCurrentStatus = driver.getDriverStatus();
//        }
//    }
    
    @Override
    public void updateOperator(Operator operator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void disableOperator(Operator operator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Car addCar(Car car, Driver driver) {
        carDAO.create(car);
        car.setDriver(driver);
        return car;
    }
    @Override
    public void updateCar(Car car) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void disableCar(Car car) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Driver addDriver(Driver driver, String operatorUserName, String image) {
        
        Date registrationDate = new Date();
        driver.setRegistrationDate(registrationDate);
        driver.getUser().setUsertype(userType.DRIVER);
        Operator operator=driverDAO.findOperator(operatorUserName);
        driver.setOperator(operator);
        driver.setImage(image);
        
        
        String emailMsg="Dear "+driver.getLastName()+",\nYour Driver account has been successfully created \n" + "Username" +driver.getUser().getUsername()+ "\n" + "Password" + driver.getUser().getPassword();
        try {
            emailSender.sendEmail(emailMsg , "USCabi Account", driver.getEmail(), "uscabimail@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        driverDAO.create(driver);
        
        
        return driver;
    }
    
    @Override
    public void updateDriver(Driver driver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void disableDriver(Driver driver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Car findCar(long id) {
        return carDAO.find(Car.class, id);
    }
    
    @Override
    public Driver findDriver(long id) {
        return driverDAO.find(Driver.class, id);
    }
    
    @Override
    public Booking findBooking(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Payment findPayment(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Car> findCars(String operatorUserName) {
        return carDAO.findAllCarByOperator(operatorUserName);
    }
    
    @Override
    public List<Driver> findDrivers(String operatorUserName) {
        return driverDAO.findAllDriverByOperator(operatorUserName);
    }
    
    @Override
    public List<Booking> findBookings(String operatorUserName) {
        return bookingDAO.findAllBookingByOperator(operatorUserName);
    }
    
    @Override
    public List<Payment> findPayments(String operatorUserName) {
        return paymentDAO.findAllPaymentByOperator(operatorUserName);
    }
    
    @Override
    public Driver findDriver(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
