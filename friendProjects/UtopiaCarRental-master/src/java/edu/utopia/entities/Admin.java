/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utopia.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fjoseph1313
 */
@Entity
@XmlRootElement
public class Admin extends Person {

    @OneToMany(mappedBy = "admin", targetEntity = Rent.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Rent> adminRentList;
    @OneToMany(mappedBy = "admin", targetEntity = Payment.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Payment> adminPayments;

    public Admin() {
    }

    public Admin(Long id, String f, String l, String p, String st, String c, String sta, String z, String e, String u, String pass) {
        super(id, f, l, p, st, c, sta, z, e, u, pass);
    }

    @XmlTransient
    public List<Rent> getAdminRentList() {
        return adminRentList;
    }

    public void setAdminRentList(List<Rent> adminRentList) {
        this.adminRentList = adminRentList;
    }

    @XmlTransient
    public List<Payment> getAdminPayments() {
        return adminPayments;
    }

    public void setAdminPayments(List<Payment> adminPayments) {
        this.adminPayments = adminPayments;
    }

}
