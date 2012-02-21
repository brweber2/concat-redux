package com.brweber2.call;

import com.brweber2.run.Invoke;
import com.brweber2.run.Stack;
import com.brweber2.type.CheckedType;
import com.brweber2.lex.Var;
import com.brweber2.type.JavaType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Set extends Invoke {

    private Var var = null;
    
    public Set(Var var, CheckedType type) {
        super(Arrays.<CheckedType>asList(new JavaType(Var.class), type), Collections.<CheckedType>emptyList());
        this.var = var;
    }

    public Set(CheckedType type) {
        super(Arrays.<CheckedType>asList(new JavaType(Var.class), type), Collections.<CheckedType>emptyList());
    }

    @Override
    protected List getOutputs(Stack stack) {
        Var name;
        if ( var != null )
        {
            name = var;
        }
        else
        {
            stack.pop(); // ignore the type
            name = (Var) stack.pop().object;
        }
        Object object = stack.pop();
        stack.set(name.var,object);
        return Collections.emptyList();
    }
}
