/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.dao;

import com.uscabi.commons.Operator;
import com.uscabi.dto.idao.GenericPersistenceDAO;
import com.uscabi.dto.idao.IGoldOperatorDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author noman-pc
 */
@Stateless
public class GoldOperatorDAO extends GenericPersistenceDAO<Operator, Long> implements IGoldOperatorDAO {

    @PersistenceContext(unitName = "USCabiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GoldOperatorDAO() {
        super(Operator.class);
    }

}
