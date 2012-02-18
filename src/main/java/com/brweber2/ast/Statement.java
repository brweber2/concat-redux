package com.brweber2.ast;

import com.brweber2.lex.Symbol;
import com.brweber2.lex.Var;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Statement {
    
    private boolean justReadAColon = false;
    private List pieces = new ArrayList();
    private List tackOntoEnd = new ArrayList();

    public List getPieces() {
        pieces.addAll(tackOntoEnd);
        return pieces;
    }

    public void add(StackEffect stackEffect) {
        addObject( stackEffect );
    }

    public void add(Items items) {
        addObject(items);
    }

    public void add(Block block) {
        addObject(block);
    }

    public void add(Symbol token) {
        addObject(token);
    }

    public void add(Var token) {
        addObject(token);
    }
    
    private void addObject( Object object )
    {
        if ( justReadAColon )
        {
            tackOntoEnd.add( object );
            justReadAColon = false;
        }
        else
        {
            pieces.add( object );
        }
    }

    public void addColon() {
        justReadAColon = true;
    }
}
