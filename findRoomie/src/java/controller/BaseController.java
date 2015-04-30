package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author xtrememe
 */
@ManagedBean
@RequestScoped
public class BaseController {
    private ExternalContext ec;
    private Flash flash;

    /**
     * Creates a new instance of BaseController
     */
    public BaseController() {
        ec = FacesContext.getCurrentInstance().getExternalContext();
        flash = ec.getFlash();
    }

    public Flash getFlash() {
        return flash;
    }


    public ExternalContext getEc() {
        return ec;
    }

    public void setEc(ExternalContext ec) {
        this.ec = ec;
    }
    
}
