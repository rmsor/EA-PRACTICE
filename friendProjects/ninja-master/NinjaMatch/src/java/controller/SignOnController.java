/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.UserFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.MemberAccount;
import util.chat.ChatView;

/**
 *
 * @author atan
 */
@ManagedBean(name = "signOnBean")
@Stateless
public class SignOnController implements Serializable {

    @EJB
    private UserFacade ejbUserFacade;

    private MemberAccount member;

    private String userName;
    private String password;
    private String errorMessage;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public MemberAccount getMember() {
        return member;
    }

    public void verifyPassword() {

        if (userName.equals("admin") && password.equals("admin")) {
            //TODO: insert admin part here
        }

        List<MemberAccount> queryList = ejbUserFacade.validateUser(getUserName(), getPassword());
        if (!(queryList.isEmpty())) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

            try {
                ec.getSessionMap().put("user", queryList.get(0));
                ec.redirect(ec.getRequestContextPath() + "/faces/pages/customer/customer_home.xhtml");
            } catch (IOException ex) {

            }
        }
    }

    public void register() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/faces/pages/customer/customer_registration.xhtml");
        } catch (IOException ex) {

        }
    }

    public void logout() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
            ec.invalidateSession();
        } catch (IOException ex) {

        }
    }

    public void logtochat() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            ec.redirect(ec.getRequestContextPath() + "/faces/pages/customer/customer_chat.xhtml");
        } catch (IOException ex) {

        }

    }

}
