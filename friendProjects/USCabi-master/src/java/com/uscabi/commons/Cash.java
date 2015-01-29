/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uscabi.commons;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author noman-pc
 */
@Entity
public class Cash extends Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    public Cash() {
    }
}
