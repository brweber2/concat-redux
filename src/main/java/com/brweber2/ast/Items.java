package com.brweber2.ast;

import com.brweber2.lex.Symbol;
import com.brweber2.lex.Var;

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
}
