/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.converter;

import com.ea.promed.entities.Client;
import com.ea.promed.facades.ClientFacade;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kunda_000
 */
@FacesConverter(forClass = Client.class, value = "clientConverter")
public class ClientConverter implements Converter {

    @EJB
    ClientFacade clientFacade;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       Long id = Long.parseLong(value);
       return clientFacade.find(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Client) value).getId());
    }
    
}
