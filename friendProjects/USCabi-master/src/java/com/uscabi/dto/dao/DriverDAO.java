/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.dao;

import com.uscabi.clientservices.IDriverService;
import com.uscabi.commons.Booking;
import com.uscabi.commons.Driver;
import com.uscabi.commons.Operator;
import com.uscabi.commons.StatusLocation;
import com.uscabi.dto.idao.GenericPersistenceDAO;
import com.uscabi.dto.idao.IDriverDAO;
import com.uscabi.dto.idao.IOperatorDAO;
import com.uscabi.dto.idao.IStatusLocationDAO;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author noman-pc
 */
@Stateless
public class DriverDAO extends GenericPersistenceDAO<Driver, Long> implements IDriverDAO, IDriverService {

    @EJB
    private IStatusLocationDAO statusLocationDAO;

    @EJB
    private IOperatorDAO operatorDAO;

    @EJB
    private IDriverDAO driverDAO;

    @PersistenceContext(unitName = "USCabiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DriverDAO() {
        super(Driver.class);
    }

    @Override
    public List<Driver> findAllDriverByOperator(String operatorUserName) {

//        CriteriaBuilder builder;
//        builder = em.getCriteriaBuilder();
//        CriteriaQuery<Driver> criteriaQuery = builder.createQuery(Driver.class);
//        Root<Driver> c;
//        c = criteriaQuery.from(Driver.class);
//        criteriaQuery.select(c).where(builder.equal(c.get("operator").get("user").get("username"), operatorUserName));
//        Query query = (Query) em.createQuery(criteriaQuery).getResultList();
//        List<Driver> drivers = query.getResultList();
        Query mq = em.createQuery("Select d from Driver d join Operator o join UserCredential u where d.operator=o AND u.username = :username");
        mq.setParameter("username", operatorUserName);
        List<Driver> drivers = mq.getResultList();
        return drivers;

    }

    @Override
    public Operator findOperator(String operatorUserName) {

        Query mq = em.createQuery("Select o from Operator o join UserCredential u where u.username = :username");
        mq.setParameter("username", operatorUserName);

        Operator operator = (Operator) mq.getSingleResult();

        return operator;
    }

    @Override
    public void addDriver(Driver driver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDriver(Driver driver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void disableDriver(Driver driver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void acceptBooking(Driver driver, Booking booking) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rejectBooking(Driver driver, Booking booking) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateLocation(StatusLocation statusLocation, String driverUserName) {
        Date statusUpdateDate = new Date();
        statusLocation.setDateAndTime(statusUpdateDate);
        Driver driver = driverDAO.findDriverByUserName(driverUserName);

        statusLocation.setDriver(driver);

        statusLocationDAO.create(statusLocation);

    }

    @Override
    public Driver findDriverByUserName(String driverUserName) {

        Query mq = em.createQuery("Select d from Driver d join UserCredential u where u.username = :username");
        mq.setParameter("username", driverUserName);

        Driver driver = (Driver) mq.getSingleResult();

        return driver;

    }

}
