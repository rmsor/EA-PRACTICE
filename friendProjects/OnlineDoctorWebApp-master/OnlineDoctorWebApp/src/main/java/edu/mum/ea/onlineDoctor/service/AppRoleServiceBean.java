/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Stella
 */
@Stateless
public class AppRoleServiceBean {
    @EJB
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
//    public AppROle findRoleByName(String name) {
//        Query query = em.createNamedQuery("findRoleByName");
//        query.setParameter("uname", name);
//        if (!query.getResultList().isEmpty()) {
//            Buyer registeredBuyer=(Buyer) query.getSingleResult();
//            return registeredBuyer;
//        }
//        return null;
//    }
}
