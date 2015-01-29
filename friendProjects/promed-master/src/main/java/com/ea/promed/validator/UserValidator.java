/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.validator;

import com.ea.promed.entities.User;
import com.ea.promed.facades.UserFacade;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author kunda_000
 */

@FacesValidator(value = "userValidator")
public class UserValidator implements Validator {

    
    @EJB
    UserFacade userFacade;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        User user = userFacade.getUserByUserName(value.toString());
        
        if(user != null)
        {
            FacesMessage msg = new FacesMessage("User Name Error:", 
						"User Name already exists.");
            throw new ValidatorException(msg);
        }
    }
    
}
