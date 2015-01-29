/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.clientservices;

import com.uscabi.commons.UserCredential;
import javax.ejb.Remote;

/**
 *
 * @author noman-pc
 */
public interface ILoginService {
    
        public UserCredential findUser(String username, String password);
    
}
