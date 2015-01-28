/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import boundaries.CategoryFacade;
import boundaries.TaxFacade;
import entities.Category;
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
public class CategoryMB implements Serializable{
    @EJB
    private TaxFacade taxFacade;
    @EJB
    private CategoryFacade categoryFacade;
    
    private Category category;
    private Long taxID;
    
    public CategoryMB() {
        this.category = new Category();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getTaxID() {
        return taxID;
    }

    public void setTaxID(Long taxID) {
        this.taxID = taxID;
    }

    public String addCategory() {
        Tax tax = (Tax)this.taxFacade.find(taxID);
        this.category.setTax(tax);
        this.categoryFacade.create(category);
        
        this.category.setCategory_name(null);
        this.category.setDescription(null);
        return "category";
    }
    
    public List<Category> getCategories() {
        return this.categoryFacade.findAll();
    }
}
