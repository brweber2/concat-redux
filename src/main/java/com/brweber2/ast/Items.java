package com.brweber2.ast;

import com.brweber2.lex.SymbolToken;
import com.brweber2.lex.VarToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Items {
    
    private List items = new ArrayList();
    
    public void add(Symbol token) {
        items.add( token );
    }

    public void add(Var token) {
        items.add( token );
    }

    public void add(Block block) {
        items.add( block );
    }

    public void add(Items items) {
        this.items.add( items );
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
