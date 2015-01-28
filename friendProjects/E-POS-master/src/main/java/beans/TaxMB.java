/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import boundaries.TaxFacade;
import entities.Tax;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author MdmRahman
 */
@Named
@RequestScoped
public class TaxMB implements Serializable{
    @EJB
    private TaxFacade taxFacade;
    private Tax tax;
    
    public TaxMB() {
        this.tax = new Tax();
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }
    
    public String addTax() {
        this.taxFacade.create(tax);
        
        this.tax.setPercentage(0);
        this.tax.setTax_name(null);
        this.tax.setTax_desc(null);
        return "tax";
    }
    
    public List<Tax> getTaxes() {
        return this.taxFacade.findAll();
    }
}
