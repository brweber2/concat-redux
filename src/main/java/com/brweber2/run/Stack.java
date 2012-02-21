package com.brweber2.run;

import com.brweber2.type.CheckedType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Stack {
    
    private List<TypeObject> objects = new ArrayList<TypeObject>();
    
    public static class TypeObject
    {
        public final CheckedType type;
        public final Object object;

        private TypeObject(CheckedType type, Object object) {
            this.type = type;
            this.object = object;
        }

        @Override
        public String toString() {
            return "TypeObject{" +
                    "type=" + type +
                    ", object=" + object +
                    '}';
        }
    }


    private Map<String,TypeObject> namedObjects = new HashMap<String, TypeObject>();
    
    public TypeObject get( String name )
    {
        return namedObjects.get( name );
    }
    
    public void set( String name, TypeObject object )
    {
        namedObjects.put( name, object );
    }
    
    public void push( CheckedType type, Object object )
    {
        objects.add(new TypeObject(type,object));
    }
    
    public List<TypeObject> peekAll()
    {
        return objects;
    }
    
    public List popAll()
    {
        List<Object> result = new ArrayList<Object>();
        for (TypeObject object : objects) {
            result.add( object.object );
        }
        return result;
    }
    
    public TypeObject pop()
    {
        return objects.remove(objects.size()-1);
    }
    
    public int size()
    {
        return objects.size();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Stack{");
        str.append("objects=" );
        for (TypeObject object : objects) {
            str.append("\n");
            str.append("  ");
            str.append(object);
        }
        str.append("\n, namedObjects=");
        for (String key : namedObjects.keySet()) {
            str.append("\n");
            str.append("  ");
            str.append(key);
            str.append(": ");
            str.append(namedObjects.get(key));
        }
        str.append("\n}");
        return str.toString();
    }

    public void clear() {
        objects.clear();
        namedObjects.clear();
    }
}
