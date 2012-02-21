package com.brweber2.run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Stack {
    
    private List objects = new ArrayList();

    private Map<String,Object> namedObjects = new HashMap<String, Object>();
    
    public Object get( String name )
    {
        return namedObjects.get( name );
    }
    
    public void set( String name, Object object )
    {
        namedObjects.put( name, object );
    }
    
    public void push( Object object )
    {
        objects.add(object);
    }
    
    public List popAll()
    {
        return objects;
    }
    
    public Object pop()
    {
        return objects.remove(objects.size()-1);
    }
    
    public int size()
    {
        return objects.size();
    }

    @Override
    public String toString() {
        return "Stack{" +
                "objects=" + objects +
                ", namedObjects=" + namedObjects +
                '}';
    }

    public void clear() {
        objects.clear();
        namedObjects.clear();
    }
}
