/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import boundaries.GroupOfRoleFacade;
import entites.GroupOfRole;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Anwar
 */
@Named(value = "namedRoleOfUser")
@RequestScoped
public class NamedRoleOfUser {
    @EJB
    private GroupOfRoleFacade groupOfRoleFacade;
    private GroupOfRole userrole;

    public GroupOfRole getUserrole() {
        return userrole;
    }

    /**
     * Creates a new instance of NamedRoleOfUser
     */
    public NamedRoleOfUser() {
        this.userrole=new GroupOfRole();
    }
    public String saveUserGroup(){
        this.groupOfRoleFacade.create(userrole);
        return "index";
    }
    public List<GroupOfRole> getAllGroup(){
        return this.groupOfRoleFacade.findAll();
    }
    
}
