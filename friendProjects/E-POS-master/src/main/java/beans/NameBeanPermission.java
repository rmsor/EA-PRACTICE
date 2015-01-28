/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import boundaries.PermissionTypeFacade;
import entites.PermissionType;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Anwar
 */
@Named(value = "nameBeanPermission")
@RequestScoped
public class NameBeanPermission {
    @EJB
    private PermissionTypeFacade permissionTypeFacade;
    
    private PermissionType permissiontype;

    public PermissionType getPermissiontype() {
        return permissiontype;
    }

    /**
     * Creates a new instance of NameBeanPermission
     */
    public NameBeanPermission() {
        this.permissiontype=new PermissionType();
    }
    public String addPermissionType(){
        this.permissionTypeFacade.create(permissiontype);
        return "index";
    }
    public List<PermissionType> getPermissionTypeList(){
        return this.permissionTypeFacade.findAll();
    }
    
}
