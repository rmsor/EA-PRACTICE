/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.dao;

import com.uscabi.clientservices.ILoginService;
import com.uscabi.commons.UserCredential;
import com.uscabi.dto.idao.GenericPersistenceDAO;
import com.uscabi.dto.idao.ILoginDAO;
import com.uscabi.dto.idao.IUserCredentialDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author noman-pc
 */
@Named
@Stateless
public class LoginDAO extends GenericPersistenceDAO<UserCredential, Long> implements ILoginDAO, ILoginService {

    @PersistenceContext(unitName = "USCabiPU")
    private EntityManager em;

    @EJB
    private IUserCredentialDAO userCredentialDAO;

    private UserCredential user;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoginDAO() {
        super(UserCredential.class);
    }

    @Override
    public UserCredential findUser(String username, String password) {
        user = userCredentialDAO.findUser(username);
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

}
