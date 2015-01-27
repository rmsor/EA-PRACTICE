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
    
    public User findUserByName(String name){       
        try {
            System.out.println("query for User with name " + name);
            Query userNameQuery = em.createNamedQuery("User.findByName");
            userNameQuery.setParameter("name", name);
            User foundUser = (User) userNameQuery.getSingleResult();
            return foundUser;
        } catch (NoResultException e) {
            System.out.println("new user "+e);
            return null;
        }
    }
    
}
