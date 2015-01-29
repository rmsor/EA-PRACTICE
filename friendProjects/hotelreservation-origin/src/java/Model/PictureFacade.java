/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.Picture;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author uurtsaikh
 */
@Stateless
public class PictureFacade extends AbstractFacade<Picture> {
    @PersistenceContext(unitName = "HotelsReservationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PictureFacade() {
        super(Picture.class);
    }
    
}
