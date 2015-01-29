/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.dao;

import com.uscabi.commons.Payment;
import com.uscabi.dto.idao.GenericPersistenceDAO;
import com.uscabi.dto.idao.IPaymentDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author noman-pc
 */
@Stateless
public class PaymentDAO extends GenericPersistenceDAO<Payment, Long> implements IPaymentDAO {

    @PersistenceContext(unitName = "USCabiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaymentDAO() {
        super(Payment.class);
    }

    @Override
    public List<Payment> findAllPaymentByOperator(String operatorUserName) {

        Query mq = em.createQuery("Select p from Payment p join Booking b join Car c join Driver d join Operator o join UserCredential u where p.booking=b AND b.car=c AND c.driver=d AND d.operator=o AND u.username = :username");
        mq.setParameter("username", operatorUserName);
        List<Payment> payments = mq.getResultList();
        return payments;

    }

}
