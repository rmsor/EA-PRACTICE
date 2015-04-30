/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author Steve
 */
public class NameValidationListener {
    
    @PrePersist
    @PreUpdate
    private void validateName(User myUser){
        if (myUser.getName() == null || "".equals(myUser.getName()))
            throw new IllegalArgumentException("Invalid user name");
    }
    
}
