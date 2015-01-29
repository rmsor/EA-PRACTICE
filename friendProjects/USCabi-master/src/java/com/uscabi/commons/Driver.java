/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.commons;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author noman-pc
 */
@Entity
public class Driver extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull
    private String drivingLicenseNo;
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date licenseExpireDate;
    @NotNull
    private String registerCouncil;
    @NotNull
    private String badgeLicenseNo;
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date badgeExpireDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    @NotNull
    private Date publicLiabilityInsuranceRenewalDate;
    @Enumerated(EnumType.STRING)
    private DriverStatus driverStatus;

    @OneToOne(mappedBy = "driver")
    private Car car;

    @ManyToOne
    private Operator operator;

    public Driver() {
    }

    public Driver(String drivingLicenseNo, Date licenseExpireDate, String registerCouncil, String badgeLicenseNo, Date badgeExpireDate, Date publicLiabilityInsuranceRenewalDate, DriverStatus driverStatus, Car car, Operator operator, String firstName, String lastName, String contactNumber, Date registrationNumber, String email, String image, Date dob, Address address, UserCredential user) {
        super(firstName, lastName, contactNumber, registrationNumber, email, image, dob, address, user);
        this.drivingLicenseNo = drivingLicenseNo;
        this.licenseExpireDate = licenseExpireDate;
        this.registerCouncil = registerCouncil;
        this.badgeLicenseNo = badgeLicenseNo;
        this.badgeExpireDate = badgeExpireDate;
        this.publicLiabilityInsuranceRenewalDate = publicLiabilityInsuranceRenewalDate;
        this.driverStatus = driverStatus;
        this.car = car;
        this.operator = operator;
    }

    public String getDrivingLicenseNo() {
        return drivingLicenseNo;
    }

    public void setDrivingLicenseNo(String drivingLicenseNo) {
        this.drivingLicenseNo = drivingLicenseNo;
    }

    public Date getLicenseExpireDate() {
        return licenseExpireDate;
    }

    public void setLicenseExpireDate(Date licenseExpireDate) {
        this.licenseExpireDate = licenseExpireDate;
    }

    public String getRegisterCouncil() {
        return registerCouncil;
    }

    public void setRegisterCouncil(String registerCouncil) {
        this.registerCouncil = registerCouncil;
    }

    public String getBadgeLicenseNo() {
        return badgeLicenseNo;
    }

    public void setBadgeLicenseNo(String badgeLicenseNo) {
        this.badgeLicenseNo = badgeLicenseNo;
    }

    public Date getBadgeExpireDate() {
        return badgeExpireDate;
    }

    public void setBadgeExpireDate(Date badgeExpireDate) {
        this.badgeExpireDate = badgeExpireDate;
    }

    public Date getPublicLiabilityInsuranceRenewalDate() {
        return publicLiabilityInsuranceRenewalDate;
    }

    public void setPublicLiabilityInsuranceRenewalDate(Date publicLiabilityInsuranceRenewalDate) {
        this.publicLiabilityInsuranceRenewalDate = publicLiabilityInsuranceRenewalDate;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

}
