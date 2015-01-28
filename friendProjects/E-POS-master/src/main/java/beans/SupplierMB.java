/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import boundaries.SupplierFacade;
import entities.Supplier;
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
public class SupplierMB implements Serializable{
    @EJB
    private SupplierFacade supplierFacade;
    private Supplier supplier;
    
    public SupplierMB() {
        this.supplier = new Supplier();
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
    public String addSupplier() {
        this.supplierFacade.create(supplier);
        
        this.supplier.setSupplier_name(null);
        this.supplier.setAddress(null);
        this.supplier.setContact1(null);
        this.supplier.setContact2(null);
        this.supplier.setEmail(null);
        return "supplier";
    }
    
    public List<Supplier> getSuppliers() {
        return this.supplierFacade.findAll();
    }
}
