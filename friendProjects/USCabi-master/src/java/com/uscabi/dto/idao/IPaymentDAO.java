/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.idao;

import com.uscabi.commons.Payment;
import java.util.List;

/**
 *
 * @author noman-pc
 */
public interface IPaymentDAO extends IGenericDAO<Payment, Long> {
    
        public List<Payment> findAllPaymentByOperator(String operatorUserName);
        
}
