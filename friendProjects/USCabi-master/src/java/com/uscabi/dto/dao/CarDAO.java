/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.dao;

import com.uscabi.commons.Car;
import com.uscabi.commons.Driver;
import com.uscabi.dto.idao.GenericPersistenceDAO;
import com.uscabi.dto.idao.ICarDAO;
import java.util.List;
import javax.ejb.Stateless;
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
@Stateless
public class CarDAO extends GenericPersistenceDAO<Car, Long> implements ICarDAO {

    @PersistenceContext(unitName = "USCabiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarDAO() {
        super(Car.class);
    }

    @Override
    public List<Car> findAllCarByOperator(String operatorUserName) {
//
//        CriteriaBuilder builder;
//        builder = em.getCriteriaBuilder();
//        CriteriaQuery<Car> criteriaQuery = builder.createQuery(Car.class);
//        Root<Car> c = criteriaQuery.from(Car.class);
//        criteriaQuery.select(c).where(builder.equal(c.get("driver").get("operator").get("user").get("username"), operatorUserName));
//        Query query = (Query) em.createQuery(criteriaQuery).getResultList();
//        List<Car> cars = query.getResultList();
//        
        Query mq = em.createQuery("Select c from Car c join Driver d join Operator o join UserCredential u where c.driver=d AND d.operator=o AND u.username = :username");
        mq.setParameter("username", operatorUserName);
        List<Car> cars = mq.getResultList();
        return cars;
        
    }

}
