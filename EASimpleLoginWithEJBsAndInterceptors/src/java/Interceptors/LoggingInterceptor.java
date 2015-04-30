/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interceptors;

import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Saeed Ahmadi
 */
public class LoggingInterceptor {
    
    @AroundConstruct
    public void init(InvocationContext context) throws Exception {

        System.out.println("Entering Constructor");
        try{
            context.proceed();
        }
        finally{
        System.out.println("Exiting Constructor");
        }
        
    }
    
    
    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {

        System.out.println("Entering : " + context.getTarget().toString()
                + " Method: " + context.getMethod().getName());
        Object ob = context.proceed();
        System.out.println("Exiting : " + context.getTarget().toString()
                + " Method: " + context.getMethod().getName());
        return ob;
    }
    
}
