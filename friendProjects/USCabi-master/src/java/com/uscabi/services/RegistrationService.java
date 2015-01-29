/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.services;

import com.uscabi.clientservices.ICustomerService;
import com.uscabi.commons.Address;
import com.uscabi.commons.Customer;
import com.uscabi.commons.UserCredential;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author noman-pc
 */
@SessionScoped
@ManagedBean
public class RegistrationService implements Serializable {

    @EJB
    private ICustomerService customerDAO;

    private Customer customer;

    @PostConstruct
    public void init() {

        this.customer = new Customer();
        customer.setAddress(new Address());
        customer.setUser(new UserCredential());

    }

    public RegistrationService() {

    }

    public String doAddCustomer() throws IOException {

        customerDAO.addCustomer(customer);
        
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer "+customer.getLastName()+" Created",null));

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Customer "+customer.getLastName()+" Created"));
        return "default.xhtml";
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
