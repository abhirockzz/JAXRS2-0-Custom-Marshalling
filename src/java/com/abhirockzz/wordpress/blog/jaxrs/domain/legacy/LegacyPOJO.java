//Maybe I am not JAXB compatible
//OR my source code is not available

package com.abhirockzz.wordpress.blog.jaxrs.domain.legacy;

public class LegacyPOJO {
    
    private String id;
    private String name;

    public LegacyPOJO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LegacyPOJO{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}
