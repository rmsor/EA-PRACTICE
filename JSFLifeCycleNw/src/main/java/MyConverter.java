/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rajesh
 */
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class MyConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       
        System.out.println("MyConverter getAsObject: " + value);
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("MyConverter getAsString: " + value);
        return (String) value;
    }
}
//getas string of converter is always called on render response note ..check it with 
//assigning value in bean for input component with some string say "5"