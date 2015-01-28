/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import beans.AutoCompleteView;
import entities.Product;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author MDZahidul
 */
@FacesConverter("productConverter")
public class ProductConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        for (Product e : AutoCompleteView.getDbSource()) {
            System.out.println("I am at getAsObject: "+e.getProduct_name());
            if (e.getProduct_name().equals(value)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {        
        Product em = (Product) value;
        
        System.out.println("getAsString: "+em.getProduct_name());
        
        return em.getProduct_name();
    }

}
