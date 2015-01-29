/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.clientservices;

import com.uscabi.commons.Registration;
import javax.ejb.Remote;

/**
 *
 * @author noman-pc
 */
public interface IEmailService {
    
    	public void sendMail(String dear, String content,String[] to);
	public void addRegistration(Registration registration);
	public void enableUser(String registrationID);
    
}
