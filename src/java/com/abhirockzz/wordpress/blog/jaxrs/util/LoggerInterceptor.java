

package com.abhirockzz.wordpress.blog.jaxrs.util;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;


public class LoggerInterceptor {
    
   @AroundInvoke
    public Object log(InvocationContext ic) throws Exception{
        
        Object obj = null;
        try {
            System.out.println("ENTERED "+ ic.getTarget().getClass().getSimpleName()+"/"+ic.getMethod().getName());
            obj = ic.proceed();
        } catch (Exception e) {
        }
        finally{
            System.out.println("EXIT "+ ic.getTarget().getClass().getSimpleName()+"/"+ic.getMethod().getName());
        }
        return obj;
    }
    
}
