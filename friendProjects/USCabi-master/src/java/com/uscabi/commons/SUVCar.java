/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.commons;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author noman-pc
 */
@Entity
public class SUVCar extends Car implements Serializable {

    private static final long serialVersionUID = 1L;

    public SUVCar() {
    }

    public SUVCar(String motCertificateNo, Date motExpirationDate, Date roadTaxExpireDate, String carInsurer, Date carInsurerRenewalDate, String publicLiabilityInsurer, String carNumber, String carMake, String carModel, int licensedSeat, String carColor, String carLicenseNo, Date carLinenseRenewalDate, double price) {
        super(motCertificateNo, motExpirationDate, roadTaxExpireDate, carInsurer, carInsurerRenewalDate, publicLiabilityInsurer, carNumber, carMake, carModel, licensedSeat, carColor, carLicenseNo, carLinenseRenewalDate, price);
    }

}
