/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import boundaries.EmployeeFacade;
import boundaries.GroupOfRoleFacade;
import entites.Employee;
import entites.GroupOfRole;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Anwar
 */
@Named(value = "namedEmployee")
@RequestScoped
public class NamedEmployee {
    @EJB
    private GroupOfRoleFacade groupOfRoleFacade;
    @EJB
    private EmployeeFacade employeeFacade;
    
    private Employee emp;
    private Long groupid;
    

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Employee getEmp() {
        return emp;
    }

    /**
     * Creates a new instance of NamedEmployee
     */
    public NamedEmployee() {
        this.emp=new Employee();
    }
    public String saveEmployee(){
        GroupOfRole grole=(GroupOfRole)this.groupOfRoleFacade.find(groupid);
        this.emp.setGrouprole(grole);
        this.employeeFacade.create(emp);
        return "index";
    }
    public List<GroupOfRole> getAllRole(){
        return this.groupOfRoleFacade.findAll();
    }
    
    
}
