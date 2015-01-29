/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.controller;

import boundary.DriverFacadeLocal;
import boundary.LocationFacadeLocal;
import com.gisfaces.model.Marker;
import entities.Driver;
import entities.Location;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author PTamang
 */
@ManagedBean(name = "driverController", eager = true)
@SessionScoped
public class DriverController implements Serializable {

    private Driver driver;
    @EJB
    private DriverFacadeLocal dfacade;

    private Location location;
    @EJB
    private LocationFacadeLocal lfacade;

    private String repass;
    private String username;
    private String password;
    MyTimer timer;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @TransactionAttribute
    public void addLocation() {
        //System.out.println(location.getLat() + " - " + location.getLon());
        location.setLat(driver.getCurrentLocation().getLat());
        location.setLon(driver.getCurrentLocation().getLon());
        location.setStreet(driver.getCurrentLocation().getStreet());
        location.setTimeStamp(new Date());
        //lfacade.create(location);
        driver.getLocation().add(location);
        dfacade.edit(driver); // update
        driver.setCurrentLocation(location);
    }

//    public List<Location> findLocations()
//    {
//        List<Location> locations = this.lfacade.findAll();
//        return locations;
//    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public DriverFacadeLocal getDfacade() {
        return dfacade;
    }

    public void setDfacade(DriverFacadeLocal dfacade) {
        this.dfacade = dfacade;
    }

    public DriverController() {
        driver = new Driver();
        location = new Location();
        //driver.setCurrentLocation(new Location());
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String checkCredentials() {
        try {
            driver = dfacade.checkCredential(username, password);
            List<Location> locationList;
            locationList = driver.getLocation();
            
            Location l = new Location();
            if (locationList.size() > 0) {
                l = locationList.get(locationList.size() - 1);
            }

            driver.setCurrentLocation(l);
            MainApp.counter += 1;
            //timer = new MyTimer();
            //timer.resetCurrentLocation();
            // timer.driverController();
            return "dashboard";
        } catch (NoResultException | NonUniqueResultException e) {
            FacesMessage msg = new FacesMessage(" Invalid Username and Password. ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "driver_login";
        }
    }

    public int getTotalUsers() {
        return MainApp.counter;
    }

    public String signUp() {
        dfacade.create(driver);
        return "driver_login";
    }

    public void saveDriver() {
        dfacade.edit(driver);
    }

    public void uploadAvatar(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file        
        try {
            String filename = event.getFile().getFileName();
            driver.setAvatar(filename);
            copyFile(filename, event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadCabImage(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file        
        try {
            String filename = event.getFile().getFileName();
            driver.setCabImage(filename);
            copyFile(filename, event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copyFile(String fileName, InputStream in) {
        String destination = "C:\\Users\\Santosh\\GlassFish_Server40\\glassfish\\domains\\domain1\\applications\\CabFinder\\resources\\avatar\\";
        try {

            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String isLoggedIn(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        if (fc.getExternalContext().getSessionMap().get("id") == null) {
            return "main";
        } else {
            return "vdriver/dashboard";
        }
    }

    public String logout() {

        return null;
    }
}
