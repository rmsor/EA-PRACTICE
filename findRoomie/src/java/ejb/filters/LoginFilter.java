package ejb.filters;

import ejb.UserFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xtrememe
 */
public class LoginFilter implements Filter{
    @EJB
    private UserFacade user;
    
    @Override
    public void destroy(){}
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
                         FilterChain filterChain) throws IOException, ServletException {
        
//        System.out.println("inside the filter method, printing the user name: " );
//        while(servletRequest.getAttributeNames().hasMoreElements()){
//            System.out.println("next element: " + servletRequest.getAttributeNames().nextElement());
//        }
        
        /**
         * Find out the id of the currently logged in user and save that into the session.
         * 
         * NOTE:
         * we cannot get hold of a session object via FacesContext 
         * because FacesContext object cannot be obtained outside of a context of
         * a JSF artifact(@ManagedBean, @FacesConverter, @FacesComponent, Phaselistener etc.)
         */
        // get a hold of the http request
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        
        // access the HttpSession associated with the request
        HttpSession session = request.getSession(request.getSession() == null ? true : false);
        
        Long userId = user.findByEmail(request.getRemoteUser().toString()).getId();
        
        session.setAttribute("userId", userId);
        session.setAttribute("userEmail", request.getRemoteUser().toString());
        
        // include the user object itself -- BUT DON'T THINK IT'S A GOOD IDEA!!!
        session.setAttribute("userObj", user.findByEmail(request.getRemoteUser().toString()));
        
        
        filterChain.doFilter(servletRequest, servletResponse);
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
