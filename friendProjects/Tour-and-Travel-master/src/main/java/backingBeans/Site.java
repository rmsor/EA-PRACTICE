/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author User
 */
@Named(value = "newJSFManagedBean")
@RequestScoped
public class Site {

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public Site() {
    }
    
}
