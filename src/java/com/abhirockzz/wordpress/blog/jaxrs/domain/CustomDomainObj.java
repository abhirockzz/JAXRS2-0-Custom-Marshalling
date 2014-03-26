//a JAXB annotated domain object
//serves as an intermediate wrapper around the legacy POJO/entity

package com.abhirockzz.wordpress.blog.jaxrs.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomDomainObj {
    
    private String myName;
    
    @XmlAttribute
    private String myId;

    public CustomDomainObj() {
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    @Override
    public String toString() {
        return "CustomDomainObj{" + "myName=" + myName + ", myId=" + myId + '}';
    }
    
    
}
