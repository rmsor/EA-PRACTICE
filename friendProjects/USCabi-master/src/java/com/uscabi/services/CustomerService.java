/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.services;

import com.uscabi.clientservices.ICustomerService;
import com.uscabi.commons.Address;
import com.uscabi.commons.Booking;
import com.uscabi.commons.Car;
import com.uscabi.commons.Customer;
import com.uscabi.commons.StatusLocation;
import com.uscabi.commons.UserCredential;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONObject;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author noman-pc
 */
@SessionScoped
@ManagedBean(name = "CustomerService")
public class CustomerService implements Serializable {

    @EJB
    private ICustomerService customerDAO;

    private String selectedIncludePath;
    
    private UploadedFile uploadedFile;

    private UserCredential user;

    private Booking booking;
    
    private Car car;
    
    private Customer customer;
    
    private Long carId;

    
    private String customerUserName;


    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @PostConstruct
    public void init() {
        this.selectedIncludePath = "/views/customer/booking.xhtml";
        
        this.user = (UserCredential) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("customerKey");
        this.customerUserName = user.getUsername();
        
        this.booking = new Booking();
    }

    public CustomerService() {

    }

    public String getSelectedIncludePath() {
        return selectedIncludePath;
    }

    public void setSelectedIncludePath(String selectedIncludePath) {
        this.selectedIncludePath = selectedIncludePath;
    }

    public String doAddBooking() {
        
        car = customerDAO.findCar(carId);
        customerDAO.addBooking(booking, car, customerUserName);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Booking Created", "The Booking " + booking.getId() + "has been created with car" + booking.getCar().getId()));
        setSelectedIncludePath("/views/customer/booking.xhtml");

        return "/secure/customer/home.xhtml";

    }

    public List doLoadDriverStatusLocation() {

        List listObject = new ArrayList();
        
        
      for (StatusLocation statusloc : customerDAO.findAllDriverStatusLocation()) {
      HashMap hm = new HashMap();
      hm.put("carid", statusloc.getDriver().getCar().getId());
      hm.put("title", statusloc.getDriver().getFirstName()+" "+statusloc.getDriver().getLastName());
      hm.put("phone", statusloc.getDriver().getContactNumber());
      hm.put("lat", statusloc.getLatitude());
      hm.put("lng", statusloc.getLongitude());
      hm.put("date", statusloc.getDateAndTime());
      
      listObject.add(new JSONObject(hm));    
        }
        return listObject;

    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public String registrationView() {

        return "Registration";
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void manageProfile(ActionEvent e) {
        setSelectedIncludePath("/views/customer/profile.xhtml");
    }

    public void createBooking(ActionEvent e) {
        setSelectedIncludePath("/views/customer/booking.xhtml");
    }

    public void bookingHistory(ActionEvent e) {
        setSelectedIncludePath("/views/customer/bookinghistory.xhtml");
    }

    public void nearestDriver(ActionEvent e) {
        setSelectedIncludePath("/views/customer/nearestDriver.xhtml");
    }

    /*Get Car Id*/
    public void addCarID(String strs) {

        System.out.println("Carid->" + strs);

    }

    public void testSampleData() {

        List driverArray = new ArrayList();
        HashMap hmap = new HashMap();
        hmap.put("title", "Md Adit Hasan");
        hmap.put("lat", "41.10289744");
        hmap.put("lng", "-91.83883667");
        hmap.put("description", "Md Adit Alibaug is a coastal town and a municipal council in Raigad District in ");

        driverArray.add(hmap);
        HashMap hmap1 = new HashMap();
        hmap1.put("title", "Md Adit Hasan");
        hmap1.put("lat", "41.10289744");
        hmap1.put("lng", "-91.83883667");
        hmap1.put("description", "Md Adit Alibaug is a coastal town and a municipal council in Raigad District in ");

    }

    public List getingMessage(){
          
      List listobj = new ArrayList();
           
      
      HashMap hm = new HashMap();
      hm.put("title", "Md adit hasan");
      hm.put("phone", "+132412341234");
      hm.put("lat", new Double(41.10289744));
      hm.put("lng", new Double(-91.83883667));
      hm.put("description", "Pune is the seventh largest metropolis in India, the second");  
      listobj.add(new JSONObject(hm));
       
      hm = new HashMap();
      hm.put("title", "Md adit hasan");
      hm.put("phone", "+132412341234");
      hm.put("lat", new Double(40.9915605));
      hm.put("lng", new Double(-92.16636658));
      hm.put("description", "Pune is the seventh largest metropolis in India, the second");
      listobj.add(new JSONObject(hm));

      hm = new HashMap();
      hm.put("title", "Md adit hasan");
      hm.put("phone", "+132412341234");
      hm.put("lat", new Double(40.98534079));
      hm.put("lng", new Double(-91.82853699));
      hm.put("description", "Pune is the seventh largest metropolis in India, the second");
      listobj.add(new JSONObject(hm));
  
     return listobj;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    
}
