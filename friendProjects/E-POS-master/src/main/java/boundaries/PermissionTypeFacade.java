/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundaries;

import entites.PermissionType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Anwar
 */
@Stateless
public class PermissionTypeFacade extends AbstractFacade<PermissionType> {
    @PersistenceContext(unitName = "OnlinePOSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermissionTypeFacade() {
        super(PermissionType.class);
    }
    
}
