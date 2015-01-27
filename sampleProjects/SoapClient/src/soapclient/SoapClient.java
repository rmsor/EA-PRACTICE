/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package soapclient;

/**
 *
 * @author Steve
 */
public class SoapClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("nepal returns " + returnOpenYourEyes("nepal"));
        System.out.println("ethiopia returns " + returnOpenYourEyes("ethiopia"));
        System.out.println("default returns " + returnOpenYourEyes("default"));
        
    }

    private static String returnOpenYourEyes(java.lang.String languageOrCountry) {
        soapclient.SlowlyOpenYourEyes_Service service = new soapclient.SlowlyOpenYourEyes_Service();
        soapclient.SlowlyOpenYourEyes port = service.getSlowlyOpenYourEyesPort();
        return port.returnOpenYourEyes(languageOrCountry);
    }
    
}
