/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.converter;

import com.ea.promed.entities.Patient;
import com.ea.promed.facades.PatientFacade;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kunda_000
 */
@FacesConverter(value = "patientConverter")
public class PatientConverter implements Converter {

    @EJB
    PatientFacade patientFacade;
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Long id = Long.parseLong(value);
        return patientFacade.find(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Patient) value).getId());
    }
    
}
