/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boundary;

import entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Steve
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "SimpleEEPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User findUserByEmail(String email){       
        try {
            System.out.println("query for User with email " + email);
            Query userNameQuery = em.createNamedQuery("User.findByEmail");
            userNameQuery.setParameter("emailAddress", email);
            User foundUser = (User) userNameQuery.getSingleResult();
            return foundUser;
        } catch (NoResultException e) {
            System.out.println("new user "+e);
            return null;
        }
    }
    
}
