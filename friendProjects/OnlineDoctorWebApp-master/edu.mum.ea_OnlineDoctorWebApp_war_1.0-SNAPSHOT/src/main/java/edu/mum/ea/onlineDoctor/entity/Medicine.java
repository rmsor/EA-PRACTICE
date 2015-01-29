/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Brook B
 */
@Entity
public class Medicine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String biologicalName;

    @OneToMany(mappedBy="medicine")
    private List<Remedy> prescribedTo;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public String getBiologicalName() {
        return biologicalName;
    }

    public void setBiologicalName(String biologicalName) {
        this.biologicalName = biologicalName;
    }

    public List<Remedy> getPrescribedTo() {
        return prescribedTo;
    }

    public void setPrescribedTo(List<Remedy> prescribedTo) {
        this.prescribedTo = prescribedTo;
    }
    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicine)) {
            return false;
        }
        Medicine other = (Medicine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.mum.ea.onlineDoctor.entity.Medicine[ id=" + id + " ]";
    }
    
}
