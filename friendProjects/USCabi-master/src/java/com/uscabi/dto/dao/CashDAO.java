/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.dao;

import com.uscabi.commons.Cash;
import com.uscabi.dto.idao.GenericPersistenceDAO;
import com.uscabi.dto.idao.ICashDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author noman-pc
 */
@Stateless
public class CashDAO extends GenericPersistenceDAO<Cash, Long> implements ICashDAO {

    @PersistenceContext(unitName = "USCabiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CashDAO() {
        super(Cash.class);
    }

}
