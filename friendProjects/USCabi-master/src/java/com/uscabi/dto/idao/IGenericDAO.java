/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uscabi.dto.idao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author noman-pc
 * @param <T>
 * @param <ID>
 */
public interface IGenericDAO<T, ID extends Serializable> {

    public void create(T entity);

    public void edit(T entity);

    public void remove(T entity);

    public T find(Class<T> entity, ID id);

    public List<T> findAll();

    public List<T> findRange(int[] range);

    public int count();

}
