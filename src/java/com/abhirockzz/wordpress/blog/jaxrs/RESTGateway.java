

package com.abhirockzz.wordpress.blog.jaxrs; 

import com.abhirockzz.wordpress.blog.jaxrs.domain.CustomDomainObj;
import com.abhirockzz.wordpress.blog.jaxrs.domain.legacy.LegacyPOJO;
import com.abhirockzz.wordpress.blog.jaxrs.util.LoggerInterceptor;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/rest")
@Interceptors(LoggerInterceptor.class)
public class RESTGateway {
    
    @Path("get")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    
    public Response get(){
        
        //imagine a search operation which returns a LegacyPOJO object
        //we can simply retrn it and leave the rest to JAXRS
        
        LegacyPOJO pojo = new LegacyPOJO("1", "name");
        //System.out.println("completed ............");
        
        return Response.ok(pojo).build();
    }
    
    @Path("post")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    
    public Response post(LegacyPOJO pojo){
        
        //we will see that the XML/JSON representation of LegacyPOJO sent from the client
        //can be seamlessly marshalled into its actual Java instance
        
        //a possible/common use case is to persist this domain object to a backend DB
        
        System.out.println("Legacy POJO obtained from the client --- "+ pojo.toString());
        return Response.ok().build();
    }
    
        @Path("fetch")
        @GET
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

        public Response fetch(){

            //directly returning an instance of the JAXB annotated class
            //JAXRS will automatically convert it to XML/JSON payload

            CustomDomainObj domainObj = new CustomDomainObj();
            domainObj.setMyId("id");
            domainObj.setMyName("name");

            return Response.ok(domainObj).build();
        }
    
    @Path("persist")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    
    public Response persist(CustomDomainObj domainObj){
        
        //the XML/JSON payload from the client get seamlessly serialized to the Java (JAXB)
        //object form which can then be introspected and maybe even persisted
        
        System.out.println("Custom domain object obtained from the client --- "+ domainObj.toString());
        
        return Response.ok().build();
    }
}
