/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.services;

import com.uscabi.clientservices.IOperatorService;
import com.uscabi.commons.Address;
import com.uscabi.commons.Booking;
import com.uscabi.commons.Car;
import com.uscabi.commons.Driver;
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
public class OperatorService implements Serializable {

    @EJB
    private IOperatorService operatorDAO;
    
    private UploadedFile uploadedFile;


    private UserCredential user;

    //private Operator operator;
    private Driver driver;

    private Car car;

    private Booking booking;

    private Payment payment;

    private Long driverId;

    private List<Driver> driverList;

    private String operatorUserName;

    private String selectedIncludePath;

    /**
     * Creates a new instance of OperatorService
     */
    public OperatorService() {
    }
//
//     public List<Driver> doDriverStatusNotification() {
//        return operatorDAO.
//    }

    @PostConstruct
    public void init() {
        this.selectedIncludePath = "/views/operator/driver.xhtml";
        //this.operatorUserName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        //this.operatorUserName = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        //System.out.print(operatorUserName);
        this.user = (UserCredential) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("operatorKey");
        this.operatorUserName = user.getUsername();
        this.driver = new Driver();
        driver.setAddress(new Address());
        driver.setUser(new UserCredential());

        this.car = new Car();

        this.driverList = operatorDAO.findDrivers(operatorUserName);

    }

    public String doAddDriver() throws IOException {
        
        String fileSource = "E:\\MSCS\\EA\\USCabiProject\\images\\" + uploadedFile.getFileName();
        String fileDestination = "E:\\MSCS\\EA\\USCabiProject\\USCabi\\web\\resources\\images\\" + uploadedFile.getFileName();
        Files.move(Paths.get(fileSource), Paths.get(fileDestination));
        String image = uploadedFile.getFileName();

        operatorDAO.addDriver(driver, operatorUserName, image);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Driver Created", "The Driver of the name " + driver.getLastName() + "has been created with id" + driver.getId()));

        setSelectedIncludePath("/views/operator/driver.xhtml");
        return "/secure/operator/home.xhtml";

    }

    public List<Driver> doFindAllDriver() {

        return operatorDAO.findDrivers(operatorUserName);

    }

    public String doAddCar() {
        
        this.driverList = operatorDAO.findDrivers(operatorUserName);

        driver = operatorDAO.findDriver(driverId);

        operatorDAO.addCar(car, driver);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Car Created", "The Car of the number " + car.getCarNumber() + "has been created with id" + car.getId()));
        setSelectedIncludePath("/views/operator/car.xhtml");
        return "/secure/operator/home.xhtml";

    }

    public List<Car> doFindAllCar() {

        return operatorDAO.findCars(operatorUserName);

    }

    public List<Booking> doFindAllBooking() {

        return operatorDAO.findBookings(operatorUserName);

    }

    public List<Payment> doFindAllPayment() {

        return operatorDAO.findPayments(operatorUserName);

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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public String getSelectedIncludePath() {
        return selectedIncludePath;
    }

    public void setSelectedIncludePath(String selectedIncludePath) {
        this.selectedIncludePath = selectedIncludePath;
    }

    public void manageCar(ActionEvent e) {
        setSelectedIncludePath("/views/operator/car.xhtml");
    }

    public void manageDriver(ActionEvent e) {
        setSelectedIncludePath("/views/operator/driver.xhtml");
    }

    public void manageBooking(ActionEvent e) {
        setSelectedIncludePath("/views/operator/bookings.xhtml");
    }

    public void managePayment(ActionEvent e) {
        setSelectedIncludePath("/views/operator/payments.xhtml");
    }
    
    public void manageMap(ActionEvent e) {
        setSelectedIncludePath("/views/operator/map.xhtml");
    }

}
