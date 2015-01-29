/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.beans;

import java.util.Map;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author kunda_000
 */

@Named
@RequestScoped
public class AbstractBean {
    
    
    FacesContext context;
    HttpServletRequest request;
    ExternalContext ec;
    Map<String, Object> sessionMap;

    public AbstractBean() {
        
        context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        ec = context.getExternalContext();
        
        sessionMap = context.getExternalContext().getSessionMap();
        
    }
    
    
    
    
    
}
