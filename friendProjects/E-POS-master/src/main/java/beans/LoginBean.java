/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entites.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MDZahidul
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    @PersistenceContext(unitName = "OnlinePOSPU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }
    private String username;
    private String password;
    private Employee emp;
    private Employee loginInfo;
    private static String employeeRole;
    private static List<String> menus= new ArrayList<>();

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public LoginBean() {
    }

    public Employee getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(Employee loginInfo) {
        this.loginInfo = loginInfo;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getMenus() {
        return menus;
    }

    /*public void setMenus(List<String> menus) {
        this.menus = menus;
    }*/

    public String userAuthentication() throws Exception {

        emp = this.check(username, password);

        if (emp != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SessionEmployee", emp);
            //System.out.println(emp.getGrouprole().getRolename());
            employeeRole = emp.getGrouprole().getRolename();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SessionEmployeeRole", employeeRole);
            generateLink();
            return "index";
        } else {

            return "loginpage";
        }

    }

    public Employee check(String username, String password) {
        Query q = em.createNamedQuery("emp.all");
        q.setParameter("username", username);
        q.setParameter("password", password);
        return (Employee) q.getSingleResult();
    }
    
    public void generateLink()
    {
        
        String role=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SessionEmployeeRole");
        System.out.println(role);
        if(role.equals("Admin"))
        {
            menus.add("Report");
            
        }
        //return menus;
    }

}
