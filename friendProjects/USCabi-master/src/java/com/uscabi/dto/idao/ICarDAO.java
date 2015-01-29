/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.idao;

import com.uscabi.commons.Car;
import com.uscabi.commons.Operator;
import java.util.List;

/**
 *
 * @author noman-pc
 */
public interface ICarDAO extends IGenericDAO<Car, Long> {

    public List<Car> findAllCarByOperator(String operatorUserName);

}
