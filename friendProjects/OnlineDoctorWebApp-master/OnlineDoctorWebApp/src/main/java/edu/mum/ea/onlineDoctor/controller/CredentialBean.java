/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.controller;

import edu.mum.ea.onlineDoctor.facade.CredentialFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Stella
 */
@Named(value = "credentialBean")
@SessionScoped
public class CredentialBean implements Serializable {
    @EJB
    private CredentialFacade creddentialFacade;
    
     /**
     * Creates a new instance of CredentialBean
     */
    public CredentialBean() {
    }
//    
//    public Credential findCredentialByUserName(){
//        if()
//    }
    
}
