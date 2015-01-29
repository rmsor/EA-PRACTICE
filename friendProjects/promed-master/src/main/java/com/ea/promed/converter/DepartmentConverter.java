/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.converter;

import com.ea.promed.entities.Department;
import com.ea.promed.facades.DepartmentFacade;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kunda_000
 */

@FacesConverter(value = "departmentConverter")
public class DepartmentConverter implements Converter {
    
    @EJB
    private DepartmentFacade departmentFacade;
    

    @Override
    public Department getAsObject(FacesContext context, UIComponent component, String value) {
        
        System.out.println("converter value"+value);
        Long id = Long.parseLong(value);
        Department dep = departmentFacade.find(id);
        return dep;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        return String.valueOf(((Department) value).getId());
    }
    
}
