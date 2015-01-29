/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.beans;

import com.ea.promed.entities.Department;
import com.ea.promed.facades.DepartmentFacade;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author kunda_000
 */
@ManagedBean
@RequestScoped
public class DepartmentBean extends AbstractBean {

    
    @EJB
    private DepartmentFacade departmentFacade;
    
    private Department department;
    
    
    public DepartmentBean() {
    }

    public Department getDepartment() {
        if(department == null)
        {
            department = new Department();
        }
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    
    public String createDepartment()
    {
        if(department.getId() != null)
        {
            departmentFacade.edit(department);
            sessionMap.put("message", "Department Info updated Successfully.");
        }else{
            departmentFacade.create(department);
            sessionMap.put("message", "Department Info added Successfully.");
        }
        return "departments?faces-redirect=true";
    }
    
    
    public List<Department> listAllDepartments()
    {
        return departmentFacade.allDepartments();
    }
    
    public void editDepartment(String departmentid) throws IOException
    {
        Long did = Long.parseLong(departmentid);
        department = departmentFacade.find(did);
        
        if(department != null)
        {
            sessionMap.put("eDepartment", department);
        }
    }
}
