/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.services;

import com.uscabi.clientservices.IAdminService;
import com.uscabi.commons.Address;
import com.uscabi.commons.Admin;
import com.uscabi.commons.Booking;
import com.uscabi.commons.Car;
import com.uscabi.commons.Customer;
import com.uscabi.commons.Driver;
import com.uscabi.commons.Operator;
import com.uscabi.commons.Payment;
import com.uscabi.commons.UserCredential;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.transaction.Transactional;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author noman-pc
 */
@ManagedBean
@SessionScoped
@Transactional
public class AdminService implements Serializable {
    
    @EJB
    private IAdminService adminDAO;

    private UploadedFile uploadedFile;
    
    private Admin admin;

    private Operator operator;

    private Driver driver;

    private Customer customer;

    private Car car;

    private Booking booking;

    private Payment payment;

    private String selectedIncludePath;

    private Long operatorId;

    private List<Operator> operatorList;

    private Long driverId;

    private List<Driver> driverList;


    public AdminService() {

    }

    @PostConstruct
    public void init() {
        this.selectedIncludePath = "/views/admin/operator.xhtml";
               
        this.admin = new Admin();
        admin.setAddress(new Address());
        admin.setUser(new UserCredential());

        this.operator = new Operator();
        operator.setAddress(new Address());
        operator.setUser(new UserCredential());

        this.driver = new Driver();
        driver.setAddress(new Address());
        driver.setUser(new UserCredential());
        driver.setOperator(new Operator());

        this.customer = new Customer();
        customer.setAddress(new Address());
        customer.setUser(new UserCredential());

        this.car = new Car();
        car.setDriver(new Driver());

        this.operatorList = adminDAO.findOperators();
        this.driverList = adminDAO.findDrivers();

    }
    
    
    public String doAddAdmin() throws IOException, Exception {

        adminDAO.addAdmin(admin);
        //adminDAO.sendMail(operator.getEmail(), emailSubject, emailMessage);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Admin Created", ""));
        setSelectedIncludePath("/views/admin/operator.xhtml");

        return "/secure/admin/home.xhtml";
    }

    //@Interceptors(LoggingAOP.class)
    public String doAddOperator() throws IOException, Exception {

        String fileSource = "E:\\MSCS\\EA\\USCabiProject\\images\\" + uploadedFile.getFileName();
        String fileDestination = "E:\\MSCS\\EA\\USCabiProject\\USCabi\\web\\resources\\images\\" + uploadedFile.getFileName();
        Files.move(Paths.get(fileSource), Paths.get(fileDestination));
        String image = uploadedFile.getFileName();

        adminDAO.addOperator(operator, image);
        //adminDAO.sendMail(operator.getEmail(), emailSubject, emailMessage);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operator Created", "The Operator of the company " + operator.getCompanyName() + "has been created with id" + operator.getId()));
        setSelectedIncludePath("/views/admin/operator.xhtml");

        return "/secure/admin/home.xhtml";
    }

    public List<Operator> doFindAllOperator() {
        return adminDAO.findOperators();
    }

    public String doAddDriver() throws IOException {
        
        
        String fileSource = "E:\\MSCS\\EA\\USCabiProject\\images\\" + uploadedFile.getFileName();
        String fileDestination = "E:\\MSCS\\EA\\USCabiProject\\USCabi\\web\\resources\\images\\" + uploadedFile.getFileName();
        Files.move(Paths.get(fileSource), Paths.get(fileDestination));
        String image = uploadedFile.getFileName();

        operator = adminDAO.findOperator(operatorId);

        adminDAO.addDriver(driver, operator, image);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Driver Created", "The Driver of the name " + driver.getLastName() + "has been created with id" + driver.getId()));
        setSelectedIncludePath("/views/admin/driver.xhtml");
        return "/secure/admin/home.xhtml";
    }

    public List<Driver> doFindAllDriver() {

        return adminDAO.findDrivers();

    }

    public String doAddCustomer() throws IOException {
                
        String fileSource = "E:\\MSCS\\EA\\USCabiProject\\images\\" + uploadedFile.getFileName();
        String fileDestination = "E:\\MSCS\\EA\\USCabiProject\\USCabi\\web\\resources\\images\\" + uploadedFile.getFileName();
        Files.move(Paths.get(fileSource), Paths.get(fileDestination));
        String image = uploadedFile.getFileName();

        adminDAO.addCustomer(customer, image);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer Created", "The Customer of the name " + customer.getLastName() + "has been created with id" + customer.getId()));
        setSelectedIncludePath("/views/admin/customer.xhtml");
        return "/secure/admin/home.xhtml";
    }

    public List<Customer> doFindAllCustomer() {

        return adminDAO.findCustomers();

    }

    public String doAddCar() {

        this.driverList = adminDAO.findDrivers();

        driver = adminDAO.findDriver(driverId);

        adminDAO.addCar(car, driver);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Car Created", "The Car of the number " + car.getCarNumber() + "has been created with id" + car.getId()));
        setSelectedIncludePath("/views/admin/car.xhtml");
        return "/secure/admin/home.xhtml";
    }

    public List<Car> doFindAllCar() {

        return adminDAO.findCars();

    }

    public List<Booking> doFindAllBooking() {

        return adminDAO.findBookings();

    }

    public List<Payment> doFindAllPayment() {

        return adminDAO.findPayments();

    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getSelectedIncludePath() {
        return selectedIncludePath;
    }

    public void setSelectedIncludePath(String selectedIncludePath) {
        this.selectedIncludePath = selectedIncludePath;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public List<Operator> getOperatorList() {
        return operatorList;
    }

    public void setOperatorList(List<Operator> operatorList) {
        this.operatorList = operatorList;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    

    public void manageOperator(ActionEvent e) {
        setSelectedIncludePath("/views/admin/operator.xhtml");
    }

    public void manageCar(ActionEvent e) {
        setSelectedIncludePath("/views/admin/car.xhtml");
    }

    public void manageCustomer(ActionEvent e) {
        setSelectedIncludePath("/views/admin/customer.xhtml");
    }

    public void manageDriver(ActionEvent e) {
        this.operatorList = adminDAO.findOperators();
        setSelectedIncludePath("/views/admin/driver.xhtml");
    }

    public void manageBooking(ActionEvent e) {
        setSelectedIncludePath("/views/admin/bookings.xhtml");
    }

    public void managePayment(ActionEvent e) {
        setSelectedIncludePath("/views/admin/payments.xhtml");
    }

    public void manageMap(ActionEvent e) {
        setSelectedIncludePath("/views/admin/map.xhtml");
    }
}
