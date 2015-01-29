/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.dao;

import com.uscabi.clientservices.ICustomerService;
import com.uscabi.commons.Booking;
import com.uscabi.commons.Car;
import com.uscabi.commons.Customer;
import com.uscabi.commons.DriverStatus;
import com.uscabi.commons.StatusLocation;
import com.uscabi.commons.userType;
import com.uscabi.dto.idao.GenericPersistenceDAO;
import com.uscabi.dto.idao.IBookingDAO;
import com.uscabi.dto.idao.ICarDAO;
import com.uscabi.dto.idao.ICustomerDAO;
import com.uscabi.dto.idao.IStatusLocationDAO;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author noman-pc
 */
@Stateless
public class CustomerDAO extends GenericPersistenceDAO<Customer, Long> implements ICustomerDAO, ICustomerService {

    @PersistenceContext(unitName = "USCabiPU")
    private EntityManager em;

    @EJB
    private ICustomerDAO customerDAO;

    @EJB
    private IBookingDAO bookingDAO;

    @EJB
    private IStatusLocationDAO statusLocationDAO;

    @EJB
    private ICarDAO carDAO;

    @EJB
    private EmailSender emailSender;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerDAO() {
        super(Customer.class);
    }

    @Override
    public Customer addCustomer(Customer customer, String image) {
        Date registrationDate = new Date();
        customer.setRegistrationDate(registrationDate);
        customer.getUser().setUsertype(userType.CUSTOMER);
        customer.setImage(image);

        customerDAO.create(customer);
        String emailMsg="Dear "+customer.getLastName()+",\n\nWelcome to USCabi.\nYour Customer account has been successfully created. \n" + "Username: " +customer.getUser().getUsername()+ "\n" + "Password: " + customer.getUser().getPassword();
        try {
            emailSender.sendEmail(emailMsg , "USCabi Account", customer.getEmail(), "uscabimail@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return customer;
    }
    
    @Override
    public Customer addCustomer(Customer customer) {
        Date registrationDate = new Date();
        customer.setRegistrationDate(registrationDate);
        customer.getUser().setUsertype(userType.CUSTOMER);

        customerDAO.create(customer);
        String emailMsg="Dear "+customer.getLastName()+",\n\nWelcome to USCabi.\nYour Customer account has been successfully created. \n" + "Username: " +customer.getUser().getUsername()+ "\n" + "Password: " + customer.getUser().getPassword();
        try {
            emailSender.sendEmail(emailMsg , "USCabi Account", customer.getEmail(), "uscabimail@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void disableCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendFeedback(Customer customer, Car car) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void payFare(Customer customer, Car car) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelBooking(Customer customer, Car car) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateLocation(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StatusLocation> findAllDriverStatusLocation() {
        Query mq = em.createQuery("Select s from StatusLocation s where s.driverStatus = :driverstatus");
        mq.setParameter("driverstatus", DriverStatus.AVAILABLE);
        List<StatusLocation> statusLocations = mq.getResultList();
        return statusLocations;

//
//        CriteriaBuilder builder;
//        builder = em.getCriteriaBuilder();
//        CriteriaQuery<StatusLocation> criteriaQuery = builder.createQuery(StatusLocation.class);
//        Root<StatusLocation> c = criteriaQuery.from(StatusLocation.class);
//        criteriaQuery.select(c).where(builder.equal(c.get("driverStatus"), DriverStatus.AVAILABLE));
//        Query query = (Query) em.createQuery(criteriaQuery).getResultList();
//        List<StatusLocation> statusLocationS = query.getResultList();
//
//        return statusLocationS;
    }

    @Override
    public Car findCar(long id) {
        return carDAO.find(Car.class, id);
    }

    @Override
    public Booking addBooking(Booking booking, Car car, String customerUserName) {

        booking.setCar(car);
        Customer customer = bookingDAO.findCustomer(customerUserName);
        booking.setCustomer(customer);
        bookingDAO.create(booking);

        String emailMsg = "Dear " + customer.getLastName() + ",\n\nYour Booking has been successfully created. You will be notified very shortly. \n\n" + "Booking Details: \n" + "Source: " + booking.getSource() + "\n" + "Destination: " + booking.getDestination() + "\n" + "Car: " + booking.getCar().getCarNumber() + "\n" + "Persons: " + booking.getNoOfPeople()+ "\n" +"Kids: "+booking.getNoOfKids()+ "\n" + "Bagages: "+booking.getNoOfBagage()+ "\n" + "Tip Amount: " + booking.getTipAmount()+"\n"+"Notes: "+booking.getNoteToDriver();
        try {
            emailSender.sendEmail(emailMsg, "USCabi Account", customer.getEmail(), "uscabimail@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return booking;

    }

}
