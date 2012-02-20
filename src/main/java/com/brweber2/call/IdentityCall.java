package com.brweber2.call;

import com.brweber2.Call;
import com.brweber2.Instructions;
import com.brweber2.Stack;
import com.brweber2.ast.StackEffect;
import com.brweber2.lex.Symbol;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class IdentityCall implements Call {
    
    private Object object;

    public IdentityCall(Object object) {
        this.object = object;
    }

    @Override
    public void invoke(Stack stack) {
        stack.push(object);
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect se = new StackEffect();
        se.addArrow();
        se.add(new Symbol(object.getClass().getName()));
        return se;
    }

    @Override
    public Instructions getInstructions() {
        return new Instructions();
    }
}
