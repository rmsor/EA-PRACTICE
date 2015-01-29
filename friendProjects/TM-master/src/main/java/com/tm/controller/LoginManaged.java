/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.controller;

import com.tm.ejb.UserFacadeLocal;
import com.tm.entities.User;
import com.tm.utils.TMRole;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sunil
 */
@ManagedBean
@SessionScoped
public class LoginManaged implements Serializable{

    private String username;
    private String password;
    private String errorMessage;
    @EJB
    private UserFacadeLocal facadeLocal;
    private boolean showAddTeacher;
    private boolean showAddStudent;
    private boolean showAddSetting;
    private boolean showCreateAppointment;
    private boolean showStudentAppointmentDate;
    private boolean showStudentTMCheckingStatus;
    private boolean showCreateStudent;

    public String checkLogin() {
        User user = facadeLocal.checkUserName(username);
        showAddTeacher = false;
        showAddStudent = false;
        showAddSetting = false;
        showCreateAppointment = false;
        showStudentAppointmentDate = false;
        showStudentTMCheckingStatus = false;
        showCreateStudent=false;
        if (user != null) {
            if (user.getPassword().equals(password)) {
                int loggedInID = 0;
                HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                sess.setAttribute("userRole", user.getRole());
               
                if (TMRole.valueOf("IT").getTmRole() == user.getRole()) {
                    loggedInID = 0;
                    showAddTeacher = true;
                    showAddStudent = true;
                    showAddSetting = true;
                    showCreateAppointment = true;
                    showStudentAppointmentDate = true;
                    showStudentTMCheckingStatus = true;
                    showCreateStudent=true;
                } else if (TMRole.valueOf("TEACHER").getTmRole() == user.getRole()) {
                    loggedInID = user.getTeacher().getId();
                    showAddTeacher = false;
                    showAddStudent = true;
                    showAddSetting = false;
                    showCreateAppointment = true;
                    showStudentAppointmentDate = false;
                    showStudentTMCheckingStatus = true;
                    showCreateStudent=false;
                } else if (TMRole.valueOf("STUDENT").getTmRole() == user.getRole()) {
                    loggedInID = user.getStudent().getId();
                    showAddTeacher = false;
                    showAddStudent = false;
                    showAddSetting = false;
                    showCreateAppointment = false;
                    showStudentAppointmentDate = true;
                    showStudentTMCheckingStatus = true;
                    showCreateStudent=false;
                } else {
                    System.out.println("Problem with role of user");
                }
                if (loggedInID >=0) {
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    session.setAttribute("userId", loggedInID);
                    return "home";
                } else {
                    errorMessage="Invalid Username/Password";
                }

            } else {
                errorMessage="Invalid Username/Password";
            }
        } else {
            errorMessage="Invalid Username/Password";
        }
        return "login";
    }

    public void logout() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        //ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
        ec.redirect("login.xhtml");
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isShowAddTeacher() {
        return showAddTeacher;
    }

    public void setShowAddTeacher(boolean showAddTeacher) {
        this.showAddTeacher = showAddTeacher;
    }

    public boolean isShowAddStudent() {
        return showAddStudent;
    }

    public void setShowAddStudent(boolean showAddStudent) {
        this.showAddStudent = showAddStudent;
    }

    public boolean isShowAddSetting() {
        return showAddSetting;
    }

    public void setShowAddSetting(boolean showAddSetting) {
        this.showAddSetting = showAddSetting;
    }

    public boolean isShowCreateAppointment() {
        return showCreateAppointment;
    }

    public void setShowCreateAppointment(boolean showCreateAppointment) {
        this.showCreateAppointment = showCreateAppointment;
    }

    public boolean isShowStudentAppointmentDate() {
        return showStudentAppointmentDate;
    }

    public void setShowStudentAppointmentDate(boolean showStudentAppointmentDate) {
        this.showStudentAppointmentDate = showStudentAppointmentDate;
    }

    public boolean isShowStudentTMCheckingStatus() {
        return showStudentTMCheckingStatus;
    }

    public void setShowStudentTMCheckingStatus(boolean showStudentTMCheckingStatus) {
        this.showStudentTMCheckingStatus = showStudentTMCheckingStatus;
    }

    public boolean isShowCreateStudent() {
        return showCreateStudent;
    }

    public void setShowCreateStudent(boolean showCreateStudent) {
        this.showCreateStudent = showCreateStudent;
    }
    

}
