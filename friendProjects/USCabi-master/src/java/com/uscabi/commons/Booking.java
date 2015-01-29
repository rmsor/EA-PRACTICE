/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.commons;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author noman-pc
 */
@Entity
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String destination;
    private String noteToDriver;
    private double tipAmount;
    @NotNull
    private int noOfPeople;
    @NotNull
    private int noOfBagage;
    private int noOfKids;
    private boolean preferDisabled;
    private boolean favouriteAddress;
    @NotNull
    private String source;

    private double distance;
    
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @OneToOne(mappedBy = "booking")
    private Payment payment;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    private Car car;

    public Booking() {
    }

    public Booking(String destination, String noteToDriver, double tipAmount, int noOfPeople, int noOfBagage, int noOfKids, boolean preferDisabled, boolean favouriteAddress, String source, double distance, BookingStatus bookingStatus, Payment payment, Customer customer, Car car) {

        this.destination = destination;
        this.noteToDriver = noteToDriver;
        this.tipAmount = tipAmount;
        this.noOfPeople = noOfPeople;
        this.noOfBagage = noOfBagage;
        this.noOfKids = noOfKids;
        this.preferDisabled = preferDisabled;
        this.favouriteAddress = favouriteAddress;
        this.source = source;
        this.distance = distance;
        this.bookingStatus = bookingStatus;
        this.payment = payment;
        this.customer = customer;
        this.car = car;
        
    }


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getNoteToDriver() {
        return noteToDriver;
    }

    public void setNoteToDriver(String noteToDriver) {
        this.noteToDriver = noteToDriver;
    }

    public double getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(double tipAmount) {
        this.tipAmount = tipAmount;
    }

    public int getNoOfPeople() {
        return noOfPeople;
    }

    public void setNoOfPeople(int noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    public int getNoOfBagage() {
        return noOfBagage;
    }

    public void setNoOfBagage(int noOfBagage) {
        this.noOfBagage = noOfBagage;
    }

    public int getNoOfKids() {
        return noOfKids;
    }

    public void setNoOfKids(int noOfKids) {
        this.noOfKids = noOfKids;
    }

    public boolean isPreferDisabled() {
        return preferDisabled;
    }

    public void setPreferDisabled(boolean preferDisabled) {
        this.preferDisabled = preferDisabled;
    }

    public boolean isFavouriteAddress() {
        return favouriteAddress;
    }

    public void setFavouriteAddress(boolean favouriteAddress) {
        this.favouriteAddress = favouriteAddress;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uscabi.entities.Booking[ id=" + id + " ]";
    }

}
