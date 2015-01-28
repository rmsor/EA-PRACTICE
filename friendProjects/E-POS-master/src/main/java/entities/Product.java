/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author MdmRahman
 */
@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String product_name;
    private String product_desc;
    private String product_type;
    private double supplier_unit_price;
    private double selling_price;
    private double discount;
    private int quantity;
    @Transient
    private Long cat_id;
//    private String product_status;
//    @Temporal(TemporalType.DATE)
//    private Date expire_date;
    private String product_image;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(String product_name, String product_desc, String product_type, double supplier_unit_price, double selling_price, int quantity, String product_image, Long catId) {
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_type = product_type;
        this.supplier_unit_price = supplier_unit_price;
        this.selling_price = selling_price;
        this.quantity = quantity;
        this.product_image = product_image;
        this.cat_id=catId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public double getSupplier_unit_price() {
        return supplier_unit_price;
    }

    public void setSupplier_unit_price(double supplier_unit_price) {
        this.supplier_unit_price = supplier_unit_price;
    }

    public double getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(double selling_price) {
        this.selling_price = selling_price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getCat_id() {
        return cat_id;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

//    public String getProduct_status() {
//        return product_status;
//    }
//
//    public void setProduct_status(String product_status) {
//        this.product_status = product_status;
//    }
//
//    public Date getExpire_date() {
//        return expire_date;
//    }
//
//    public void setExpire_date(Date expire_date) {
//        this.expire_date = expire_date;
//    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Product[ id=" + id + " ]";
    }
    
}
