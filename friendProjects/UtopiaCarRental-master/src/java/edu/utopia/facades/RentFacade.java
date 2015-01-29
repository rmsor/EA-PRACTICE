/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utopia.facades;

import edu.utopia.entities.Rent;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fjoseph1313
 */
@Stateless
public class RentFacade extends AbstractFacade<Rent> {

    @PersistenceContext(unitName = "UtopiaCarRentalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RentFacade() {
        super(Rent.class);
    }

    public List<Rent> findRequestedRents() {
        Query query = em.createQuery("From Rent r WHERE r.rentStatus='requested'");
        return query.getResultList();
    }

    public Rent findRentByReservationCode(int reservationCode) {
        Query query = em.createQuery("From Rent r WHERE r.reservationCode=:reservationCode");
        query.setParameter("reservationCode", reservationCode);
        return (Rent) query.getSingleResult();
    }
}
