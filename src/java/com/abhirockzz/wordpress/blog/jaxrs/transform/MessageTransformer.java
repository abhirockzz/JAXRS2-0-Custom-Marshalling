//this transformation class contains the logic for
//converting the legacy POJO into a meaningful domain object to
//be transmitted over the wire by the JAXRS implementation

package com.abhirockzz.wordpress.blog.jaxrs.transform;

import com.abhirockzz.wordpress.blog.jaxrs.domain.CustomDomainObj;
import com.abhirockzz.wordpress.blog.jaxrs.domain.legacy.LegacyPOJO;
import com.abhirockzz.wordpress.blog.jaxrs.util.LoggerInterceptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


@Provider
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Interceptors(LoggerInterceptor.class)

public class MessageTransformer implements MessageBodyReader<LegacyPOJO>, MessageBodyWriter<LegacyPOJO>{

    
    //implementation for MessageBodyReader interface
    
    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        
        //return true unconditionally - not ideal. one can include checks
        return true;
    }

    @Override
    public LegacyPOJO readFrom(Class<LegacyPOJO> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, String> mm, InputStream in) throws IOException, WebApplicationException {
        
        JAXBContext context = null;
        Unmarshaller toJavaObj = null;
        CustomDomainObj javaDomainObject = null;
        LegacyPOJO pojo = null;
        
        try {
            // unmarshall from XML/JSON to Java object

             context = JAXBContext.newInstance(CustomDomainObj.class);
            toJavaObj = context.createUnmarshaller();
             javaDomainObject = (CustomDomainObj) toJavaObj.unmarshal(in);
            pojo = new LegacyPOJO(javaDomainObject.getMyId(), javaDomainObject.getMyName());
        } catch (JAXBException ex) {
            Logger.getLogger(MessageTransformer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pojo;
    }

    //implementation for MessageBodyWriter interface
    
    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
       //return true unconditionally - not ideal. one can include checks
        return true;
    }

    @Override
    public long getSize(LegacyPOJO t, Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        
        //if you are unsure about how to calculate this
        return -1;
    }

    @Override
    public void writeTo(LegacyPOJO t, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        
        JAXBContext context = null;
        Marshaller toXML = null;
        CustomDomainObj javaDomainObject = new CustomDomainObj();
        
        javaDomainObject.setMyId(t.getId());
        javaDomainObject.setMyName(t.getName());
        
        try {
            // marshall from XML/JSON to Java object

             context = JAXBContext.newInstance(CustomDomainObj.class);
            toXML = context.createMarshaller();
             toXML.marshal(javaDomainObject, out);
           
        } catch (JAXBException ex) {
            Logger.getLogger(MessageTransformer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
