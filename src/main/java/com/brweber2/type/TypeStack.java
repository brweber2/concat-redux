package com.brweber2.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class TypeStack {

    private List<CheckedType> typeStack = new ArrayList<CheckedType>();

    public List<CheckedType> all()
    {
        return typeStack;
    }
    
    public void add( CheckedType type )
    {
        typeStack.add( type );
    }

    public CheckedType peek( int offsetFromTheEnd )
    {
        if ( offsetFromTheEnd >= 0 )
        {
            throw new RuntimeException("You developer, must learn how to use this API first!");
        }
        if ( offsetFromTheEnd * -1 > typeStack.size() )
        {
            throw new RuntimeException("There are not that many items on the type stack.");
        }
        return typeStack.get(typeStack.size()+offsetFromTheEnd);
    }

    public CheckedType pop()
    {
        return typeStack.remove(typeStack.size()-1);
    }

}
