/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundaries;

import entities.Product;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MdmRahman
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "OnlinePOSPU")
    private EntityManager em;
    private Logger logger = Logger.getLogger("MavenOnlinePOS");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    @AroundInvoke
    private Object logMethod(InvocationContext ic) throws Exception {
        logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
        
        try {
            logger.info("Entering  calling method");
            return ic.proceed();

        } finally {
            logger.info("Getting out from method");
            logger.exiting(ic.getTarget().toString(), ic.getMethod().getName());
           
        }
    }

}
