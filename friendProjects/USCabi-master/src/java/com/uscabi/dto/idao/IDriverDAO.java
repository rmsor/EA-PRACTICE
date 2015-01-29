/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.idao;

import com.uscabi.commons.Driver;
import com.uscabi.commons.Operator;
import java.util.List;

/**
 *
 * @author noman-pc
 */
public interface IDriverDAO extends IGenericDAO<Driver, Long> {

    public List<Driver> findAllDriverByOperator(String operatorUserName);
    
    public Operator findOperator(String username);
    
    public Driver findDriverByUserName(String driverUserName);

}
