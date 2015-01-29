/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Stella
 */
@Named(value = "roleBean")
@SessionScoped
public class roleBean implements Serializable {

    /**
     * Creates a new instance of roleBean
     */
    public roleBean() {
    }
    
}
