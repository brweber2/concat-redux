package com.brweber2.call;

import com.brweber2.run.Call;
import com.brweber2.run.Instructions;
import com.brweber2.run.Stack;
import com.brweber2.ast.StackEffect;
import com.brweber2.lex.Symbol;
import com.brweber2.type.CheckedType;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class IdentityCall implements Call {

    private CheckedType type;
    private Object object;

    public IdentityCall(CheckedType type, Object object) {
        this.type = type;
        this.object = object;
    }

    @Override
    public void invoke(Stack stack) {
        stack.push(type,object);
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
