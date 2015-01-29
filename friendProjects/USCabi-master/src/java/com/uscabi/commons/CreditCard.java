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
import javax.persistence.Table;

/**
 *
 * @author noman-pc
 */
@Entity
@Table(name="credit_card")
public class CreditCard extends Payment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Enumerated(EnumType.STRING)
    private CreditCardType creditCardType;

    public CreditCard() {
    }

    public CreditCard(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }
    
}
