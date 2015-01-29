package com.uscabi.dto.dao;

import com.uscabi.clientservices.IAdminService;
import com.uscabi.commons.Admin;
import com.uscabi.commons.Booking;
import com.uscabi.commons.Car;
import com.uscabi.commons.Customer;
import com.uscabi.commons.Driver;
import com.uscabi.commons.Operator;
import com.uscabi.commons.Payment;
import com.uscabi.commons.UserCredential;
import com.uscabi.commons.userType;
import com.uscabi.dto.idao.GenericPersistenceDAO;
import com.uscabi.dto.idao.IAdminDAO;
import com.uscabi.dto.idao.IBookingDAO;
import com.uscabi.dto.idao.ICarDAO;
import com.uscabi.dto.idao.ICustomerDAO;
import com.uscabi.dto.idao.IDriverDAO;
import com.uscabi.dto.idao.IOperatorDAO;
import com.uscabi.dto.idao.IPaymentDAO;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author noman-pc
 */
@Named
@Stateless
//@RolesAllowed({"ADMIN"})
public class AdminDAO extends GenericPersistenceDAO<Admin, Long> implements IAdminDAO, IAdminService {

    @PersistenceContext(unitName = "USCabiPU")
    private EntityManager em;

    @Resource
    private SessionContext context;

    @EJB
    private IAdminDAO adminDAO;

    @EJB
    private IOperatorDAO operatorDAO;

    @EJB
    private IDriverDAO driverDAO;

    @EJB
    private ICustomerDAO customerDAO;

    @EJB
    private ICarDAO carDAO;

    @EJB
    private IBookingDAO bookingDAO;

    @EJB
    private IPaymentDAO paymentDAO;

    @EJB
    private EmailSender emailSender;

    private Payment payment;

    // private IUserCredentialDAO userDAO;
    //private IOperatorDAO operatorDAO;
    private UserCredential userCredential;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminDAO() {
        super(Admin.class);
    }

    @Schedules({
        @Schedule(hour = "0", timezone = "US/Central"),
        @Schedule(dayOfMonth = "1", hour = "0", timezone = "US/Central")})
    public void generateReport() {

        List<Booking> allBooking = bookingDAO.findAll();
        Iterator<Booking> itr = allBooking.iterator();
        while (itr.hasNext()) {
            Booking booking = itr.next();
            payment = booking.getPayment();
        }

    }

    @Override
    public Admin addAdmin(Admin admin) {
        //this.adminDAO.create(admin);
        Date registrationDate = new Date();
        admin.setRegistrationDate(registrationDate);
        admin.getUser().setUsertype(userType.ADMIN);
        adminDAO.create(admin);

        return admin;

    }

    @Override
    public Operator addOperator(Operator operator, String image) {
//        if (!context.isCallerInRole("ADMIN")) {
//            throw new SecurityException("Only Admin can create Operator!!!");
//        } else {
        Date registrationDate = new Date();
        operator.setRegistrationDate(registrationDate);
        operator.getUser().setUsertype(userType.OPERATOR);
        operator.setCompanyName("USCabi");
        operator.setImage(image);
        operatorDAO.create(operator);
        String emailMsg = "Dear " + operator.getLastName() + ",\n\nYour Operator account has been successfully created \n" + "Username: " + operator.getUser().getUsername() + "\n" + "Password: " + operator.getUser().getPassword();
        try {
            emailSender.sendEmail(emailMsg, "USCabi Account", operator.getEmail(), "uscabimail@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return operator;

    }

    @Override
    public void updateOperator(Operator operator) {

    }

    @Override
    public void disableOperator(Operator operator) {

    }

    @Override
    public Customer addCustomer(Customer customer, String image) {
        Date registrationDate = new Date();
        customer.setRegistrationDate(registrationDate);
        customer.getUser().setUsertype(userType.CUSTOMER);
        customer.setImage(image);

        customerDAO.create(customer);
        String emailMsg = "Dear " + customer.getLastName() + ",\nYour Customer account has been successfully created \n" + "Username" + customer.getUser().getUsername() + "\n" + "Password" + customer.getUser().getPassword();
        try {
            emailSender.sendEmail(emailMsg, "USCabi Account", customer.getEmail(), "uscabimail@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void disableCustomer(Customer customer) {

    }

    @Override
    public Car addCar(Car car, Driver driver) {
        car.setDriver(driver);
        carDAO.create(car);
        return car;
    }

    @Override
    public void updateCar(Car car) {

    }

    @Override
    public void disableCar(Car car) {

    }

    @Override
    public Driver addDriver(Driver driver, Operator operator, String image) {
        Date registrationDate = new Date();
        driver.setRegistrationDate(registrationDate);
        driver.getUser().setUsertype(userType.DRIVER);
        driver.setImage(image);
        driver.setOperator(operator);

        driverDAO.create(driver);

        String emailMsg = "Dear " + driver.getLastName() + ",\nYour Driver account has been successfully created \n" + "Username" + driver.getUser().getUsername() + "\n" + "Password" + driver.getUser().getPassword();
        try {
            emailSender.sendEmail(emailMsg, "USCabi Account", driver.getEmail(), "uscabimail@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return driver;
    }

    @Override
    public void updateDriver(Driver driver) {

    }

    @Override
    public void disableDriver(Driver driver) {

    }

    @Override
    public Operator findOperator(long id) {
        return operatorDAO.find(Operator.class, id);
    }

    @Override
    public Customer findCustomer(long id) {
        return customerDAO.find(Customer.class, id);
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
        return bookingDAO.find(Booking.class, id);
    }

    @Override
    public Payment findPayment(long id) {
        return paymentDAO.find(Payment.class, id);
    }

    @Override
    public List<Customer> findCustomers() {
        return customerDAO.findAll();
    }

    @Override
    public List<Operator> findOperators() {
        return operatorDAO.findAll();
    }

    @Override
    public List<Car> findCars() {
        return carDAO.findAll();
    }

    @Override
    public List<Driver> findDrivers() {
        return driverDAO.findAll();
    }

    @Override
    public List<Booking> findBookings() {
        return bookingDAO.findAll();
    }

    @Override
    public List<Payment> findPayments() {
        return paymentDAO.findAll();
    }

    @Override
    public Customer findCustomer(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Operator findOperator(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Admin findAdmin(String username) {
        // return admin;
        return null;
    }

    @Override
    public Driver findDriver(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserCredential findUser(String username) {
        return userCredential;
    }
}