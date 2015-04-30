
package soapclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for returnOpenYourEyes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="returnOpenYourEyes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="languageOrCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "returnOpenYourEyes", propOrder = {
    "languageOrCountry"
})
public class ReturnOpenYourEyes {

    protected String languageOrCountry;

    /**
     * Gets the value of the languageOrCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageOrCountry() {
        return languageOrCountry;
    }

    /**
     * Sets the value of the languageOrCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageOrCountry(String value) {
        this.languageOrCountry = value;
    }

}
