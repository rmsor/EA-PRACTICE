/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import boundaries.PermissionFacade;
import entites.Permission;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author ajahan
 */
@Named(value = "userNameBean")
@RequestScoped
public class UserNameBean {
    @EJB
    private PermissionFacade permissionFacade;
    
    private Permission permission;

   
   
    public Permission getPermission() {
        return permission;
    }

    /**
     * Creates a new instance of UserNameBean
     */
    public UserNameBean() {
        this.permission=new Permission();
    }

    public String addNewPermission(){
        return "permission";
    }
    public String addAuthorizationGroup(){
        return "usergroup";
    }
    public String addEmployees(){
        return "register";
    }
    public String addLoginPage(){
        return "loginpage";
    }
    public String homePage(){
        return "index";
    }
    public String addPermission(){
        this.permissionFacade.create(permission);
        return "index";
    }
    public List<Permission> listPermission(){
        return this.permissionFacade.findAll();
    }
     public String addPermissionOptionPage(){
        return "permissionoption";
    }
}
