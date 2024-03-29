package com.brweber2.ast;

import com.brweber2.lex.SymbolToken;
import com.brweber2.lex.VarToken;
import com.brweber2.run.Call;
import com.brweber2.type.CheckedType;
import com.brweber2.type.TypeSystem;

import java.util.ArrayList;
import java.util.Collections;
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

    public void add(CheckedType type) {
        if ( arrowCount == 0 )
        {
            beforeArrow.add( type );
        }
        else
        {
            afterArrow.add(type);
        }
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
        for (int i = beforeArrow.size()-1; i >= 0; i-- ) {
            Object o = beforeArrow.get(i);
            if ( o instanceof CheckedType )
            {
                types.add( (CheckedType) o );
            }
            else if ( o instanceof Symbol)
            {
                types.add(TypeSystem.findType(((Symbol)o).symbol));
            }
            else if ( o instanceof Var)
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
            if ( o instanceof CheckedType )
            {
                types.add( (CheckedType) o );
            }
            else if ( o instanceof Symbol)
            {
                types.add(TypeSystem.findType(((Symbol)o).symbol));
            }
            else if ( o instanceof Var)
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

    public List<Call> getInstructions() {
        List<Call> calls = new ArrayList<Call>();
        VarToken var = null;
        for (Object o : beforeArrow) {
            if ( o instanceof Symbol)
            {
                if ( var != null )
                {
                    CheckedType type = TypeSystem.findType(((Symbol)o).symbol);
//                    calls.add( new Set(var,type) );
                    // todo figure this out!!!
                }
                var = null;
            }
            else if ( o instanceof Var)
            {
                var = (VarToken) o;
            }
            else
            {
                throw new RuntimeException("Invalid stack effect " + this  + " because a non symbol/var was found.");
            }
        }
        return calls;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("( ");
        for (Object o : beforeArrow) {
            str.append( o );
            str.append( " " );
        }
        str.append("-> " );
        for (Object o : afterArrow) {
            str.append( o );
            str.append( " " );
        }
        str.append(" )");
        return str.toString();
    }
}
