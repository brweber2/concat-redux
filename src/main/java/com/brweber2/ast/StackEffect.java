package com.brweber2.ast;

import com.brweber2.Call;
import com.brweber2.CheckedType;
import com.brweber2.Instructions;
import com.brweber2.call.Set;
import com.brweber2.lex.Symbol;
import com.brweber2.lex.Var;
import com.brweber2.type.TypeSystem;

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
        return arrowCount == 1;
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

    public List<CheckedType> getInputTypes() {
        if ( !isValid() )
        {
            throw new RuntimeException("Invalid stack effect " + this);
        }
        List<CheckedType> types = new ArrayList<CheckedType>();
        for (Object o : beforeArrow) {
            if ( o instanceof Symbol )
            {
                types.add(TypeSystem.findType(((Symbol)o).symbol));
            }
            else if ( o instanceof  Var )
            {
                // ok
            }
            else
            {
                throw new RuntimeException("Invalid stack effect " + this  + " because a non symbol/var was found.");
            }
        }
        return types;
    }

    public List<CheckedType> getOutputTypes() {
        if ( !isValid() )
        {
            throw new RuntimeException("Invalid stack effect " + this);
        }
        List<CheckedType> types = new ArrayList<CheckedType>();
        for (Object o : afterArrow) {
            if ( o instanceof Symbol )
            {
                types.add(TypeSystem.findType(((Symbol)o).symbol));
            }
            else if ( o instanceof  Var )
            {
                // ok
            }
            else
            {
                throw new RuntimeException("Invalid stack effect " + this  + " because a non symbol/var was found.");
            }
        }
        return types;
    }

    public Instructions getInstructions() {
        List<Call> calls = new ArrayList<Call>();
        Var var = null;
        for (Object o : beforeArrow) {
            if ( o instanceof Symbol )
            {
                if ( var != null )
                {
                    CheckedType type = TypeSystem.findType(((Symbol)o).symbol);
                    calls.add( new Set(var,type) );
                }
                var = null;
            }
            else if ( o instanceof  Var )
            {
                var = (Var) o;
            }
            else
            {
                throw new RuntimeException("Invalid stack effect " + this  + " because a non symbol/var was found.");
            }
        }
        return new Instructions(calls);
    }
}