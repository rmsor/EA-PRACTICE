/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.idao;

import com.uscabi.commons.Booking;
import com.uscabi.commons.Customer;
import java.util.List;

/**
 *
 * @author noman-pc
 */
public interface IBookingDAO extends IGenericDAO<Booking, Long> {
        
    public List<Booking> findAllBookingByOperator(String operatorUserName);
    
    public Customer findCustomer(String customerUserName);


}
