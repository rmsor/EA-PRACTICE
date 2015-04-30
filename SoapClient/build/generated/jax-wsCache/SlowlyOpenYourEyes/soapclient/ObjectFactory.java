
package soapclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soapclient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReturnOpenYourEyes_QNAME = new QName("http://webServices/", "returnOpenYourEyes");
    private final static QName _ReturnOpenYourEyesResponse_QNAME = new QName("http://webServices/", "returnOpenYourEyesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soapclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReturnOpenYourEyes }
     * 
     */
    public ReturnOpenYourEyes createReturnOpenYourEyes() {
        return new ReturnOpenYourEyes();
    }

    /**
     * Create an instance of {@link ReturnOpenYourEyesResponse }
     * 
     */
    public ReturnOpenYourEyesResponse createReturnOpenYourEyesResponse() {
        return new ReturnOpenYourEyesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnOpenYourEyes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "returnOpenYourEyes")
    public JAXBElement<ReturnOpenYourEyes> createReturnOpenYourEyes(ReturnOpenYourEyes value) {
        return new JAXBElement<ReturnOpenYourEyes>(_ReturnOpenYourEyes_QNAME, ReturnOpenYourEyes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnOpenYourEyesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "returnOpenYourEyesResponse")
    public JAXBElement<ReturnOpenYourEyesResponse> createReturnOpenYourEyesResponse(ReturnOpenYourEyesResponse value) {
        return new JAXBElement<ReturnOpenYourEyesResponse>(_ReturnOpenYourEyesResponse_QNAME, ReturnOpenYourEyesResponse.class, null, value);
    }

}
