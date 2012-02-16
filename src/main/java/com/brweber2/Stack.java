package com.brweber2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Stack {
    
    private List objects = new ArrayList();
    
    // todo add named objects?
    
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
    
    // peeks at object size - i
    public Object peek(int i )
    {
        int index = objects.size() - 1 - i;
        if ( index < 0 )
        {
            throw new RuntimeException("Unable to peek before the beginning of the stack.");
        }
        return objects.get(index);
    }
    
    public int size()
    {
        return objects.size();
    }
}
