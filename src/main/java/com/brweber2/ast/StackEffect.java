package com.brweber2.ast;

import com.brweber2.lex.Symbol;
import com.brweber2.lex.Var;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class StackEffect {

    private int arrowCount = 0;
    private List beforeArrow = new ArrayList();
    private List afterArrow = new ArrayList();

    public boolean isValid()
    {
        return arrowCount == 1 && !beforeArrow.isEmpty() && !afterArrow.isEmpty();
    }

    public void add(Symbol token) {
        if ( arrowCount == 0 )
        {
            beforeArrow.add( token );
        }
        else
        {
            afterArrow.add(token);
        }
    }

    public void add(Var token) {
        if ( arrowCount == 0 )
        {
            beforeArrow.add( token );
        }
        else
        {
            afterArrow.add(token);
        }
    }

    public void addArrow() {
        arrowCount++;
    }
}
