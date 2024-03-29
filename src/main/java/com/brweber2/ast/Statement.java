package com.brweber2.ast;

import com.brweber2.lex.LexToken;

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
    private boolean concatAlready = false;
    private LexToken terminatingToken;

    public Statement() {
    }

    public List getArgs()
    {
        List pieces = getPieces();
        return pieces.subList(0, pieces.size() -1);
    }
    
    public List getPieces() {
        if ( concatAlready )
        {
            return pieces;
        }
        pieces.addAll(tackOntoEnd);
        concatAlready = true;
        return pieces;
    }

    public void add( Object o )
    {
        addObject(o);
    }

    public void add( NumberLiteral numberLiteral )
    {
        addObject(numberLiteral);
    }

    public void add( StringLiteral stringLiteral )
    {
        addObject(stringLiteral);
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

    public String getName() {
        List list = getPieces();
        if ( !list.isEmpty() )
        {
            Object last = list.get(list.size()-1);
            if ( last instanceof Symbol )
            {
                return ((Symbol)last).symbol;
            }
        }
        return null;
    }

    public void setTerminatingToken( LexToken token )
    {
        terminatingToken = token;
    }

    public LexToken getTerminatingToken() {
        return terminatingToken;
    }
}
