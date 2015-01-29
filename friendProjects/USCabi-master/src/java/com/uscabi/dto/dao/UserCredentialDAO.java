/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.dao;

import com.uscabi.commons.UserCredential;
import com.uscabi.commons.UserCredential_;
import com.uscabi.dto.idao.GenericPersistenceDAO;
import com.uscabi.dto.idao.IUserCredentialDAO;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author noman-pc
 */
@Named
@Stateless
public class UserCredentialDAO extends GenericPersistenceDAO<UserCredential, Long> implements IUserCredentialDAO {

    @PersistenceContext(unitName = "USCabiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserCredentialDAO() {
        super(UserCredential.class);
    }

    @Override
    public UserCredential findUser(String username) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<UserCredential> criteriaQuery = builder.createQuery(UserCredential.class);
        Root<UserCredential> c = criteriaQuery.from(UserCredential.class);
        criteriaQuery.select(c).where(builder.equal(c.get(UserCredential_.username), username));
        Query query = em.createQuery(criteriaQuery);
        UserCredential user = (UserCredential) query.getSingleResult();
        return user;
    }

}
