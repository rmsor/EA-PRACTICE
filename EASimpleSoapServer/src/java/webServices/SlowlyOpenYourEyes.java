/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webServices;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Steve
 */
@WebService(serviceName = "SlowlyOpenYourEyes")
@Stateless()
public class SlowlyOpenYourEyes {

    /**
     * Web service operation
     */
    private String returnString; 
    @WebMethod(operationName = "returnOpenYourEyes")
    public String returnOpenYourEyes(@WebParam(name = "languageOrCountry") String languageOrCountry) {
        //base on country or language return slowly open your eyes translation
        //default is english
        returnString = "slowly open your eyes";
        if (languageOrCountry.equals("nepal"))
            returnString = "bistarai akha kholnuhosh";
        if(languageOrCountry.equals("ethiopia"))
            returnString = "kes bilachuh aynachuhun kifetu";
        return returnString;
    }
}
