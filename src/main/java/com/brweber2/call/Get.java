package com.brweber2.call;

import com.brweber2.lex.Symbol;
import com.brweber2.run.Invoke;
import com.brweber2.run.Stack;
import com.brweber2.type.CheckedType;
import com.brweber2.lex.Var;
import com.brweber2.type.JavaType;

import java.util.Arrays;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Get extends Invoke {
    public Get(CheckedType type) {
        super(Arrays.<CheckedType>asList(new JavaType(Var.class),new JavaType(Symbol.class)), Arrays.asList(type));
    }

    @Override
    protected List getOutputs(Stack stack) {
        stack.pop(); // ignore the type
        Var toGet = (Var) stack.pop().object;
        return Arrays.asList( stack.get(toGet.var) );
    }
}
