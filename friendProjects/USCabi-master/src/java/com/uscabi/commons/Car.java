/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.commons;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author noman-pc
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String motCertificateNo;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date motExpirationDate;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date roadTaxExpireDate;
    @NotNull
    private String carInsurer;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date carInsurerRenewalDate;
    private String publicLiabilityInsurer;
    @NotNull
    private String carNumber;
    private String carMake;
    private String carModel;
    @NotNull
    private int licensedSeat;
    private String carColor;
    private String carLicenseNo;
    @Temporal(TemporalType.DATE)
    private Date carLinenseRenewalDate;
    private double price;
    @NotNull
    @Enumerated(EnumType.STRING)
    private carType carType;

    @OneToMany(mappedBy = "car")
    private List<Booking> bookings;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public Car() {
    }

    public Car(String motCertificateNo, Date motExpirationDate, Date roadTaxExpireDate, String carInsurer, Date carInsurerRenewalDate, String publicLiabilityInsurer, String carNumber, String carMake, String carModel, int licensedSeat, String carColor, String carLicenseNo, Date carLinenseRenewalDate, double price) {
        this.motCertificateNo = motCertificateNo;
        this.motExpirationDate = motExpirationDate;
        this.roadTaxExpireDate = roadTaxExpireDate;
        this.carInsurer = carInsurer;
        this.carInsurerRenewalDate = carInsurerRenewalDate;
        this.publicLiabilityInsurer = publicLiabilityInsurer;
        this.carNumber = carNumber;
        this.carMake = carMake;
        this.carModel = carModel;
        this.licensedSeat = licensedSeat;
        this.carColor = carColor;
        this.carLicenseNo = carLicenseNo;
        this.carLinenseRenewalDate = carLinenseRenewalDate;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotCertificateNo() {
        return motCertificateNo;
    }

    public void setMotCertificateNo(String motCertificateNo) {
        this.motCertificateNo = motCertificateNo;
    }

    public Date getMotExpirationDate() {
        return motExpirationDate;
    }

    public void setMotExpirationDate(Date motExpirationDate) {
        this.motExpirationDate = motExpirationDate;
    }

    public Date getRoadTaxExpireDate() {
        return roadTaxExpireDate;
    }

    public void setRoadTaxExpireDate(Date roadTaxExpireDate) {
        this.roadTaxExpireDate = roadTaxExpireDate;
    }

    public String getCarInsurer() {
        return carInsurer;
    }

    public void setCarInsurer(String carInsurer) {
        this.carInsurer = carInsurer;
    }

    public Date getCarInsurerRenewalDate() {
        return carInsurerRenewalDate;
    }

    public void setCarInsurerRenewalDate(Date carInsurerRenewalDate) {
        this.carInsurerRenewalDate = carInsurerRenewalDate;
    }

    public String getPublicLiabilityInsurer() {
        return publicLiabilityInsurer;
    }

    public void setPublicLiabilityInsurer(String publicLiabilityInsurer) {
        this.publicLiabilityInsurer = publicLiabilityInsurer;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getLicensedSeat() {
        return licensedSeat;
    }

    public void setLicensedSeat(int licensedSeat) {
        this.licensedSeat = licensedSeat;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarLicenseNo() {
        return carLicenseNo;
    }

    public void setCarLicenseNo(String carLicenseNo) {
        this.carLicenseNo = carLicenseNo;
    }

    public Date getCarLinenseRenewalDate() {
        return carLinenseRenewalDate;
    }

    public void setCarLinenseRenewalDate(Date carLinenseRenewalDate) {
        this.carLinenseRenewalDate = carLinenseRenewalDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public carType getCarType() {
        return carType;
    }

    public void setCarType(carType carType) {
        this.carType = carType;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Booking booking) {
        this.bookings.add(booking);
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
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
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uscabi.entities.Car[ id=" + id + " ]";
    }

}
