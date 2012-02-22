package com.brweber2.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Items {
    
    private List<Object> items = new ArrayList<Object>();
    
    public void add(Object object) {
        items.add( object );
    }

    public List getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[ " );
        for ( int i = 0; i < items.size(); i++ ) {
            str.append(items.get(i));
            if ( i != items.size() -1 )
            {
                str.append(",");
            }
        }
        str.append(" ]");
        return str.toString();
    }
}
