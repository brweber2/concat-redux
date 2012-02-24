package com.brweber2.call.literal;

import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.ast.StackEffect;
import com.brweber2.type.CheckedType;
import com.brweber2.type.TypeStack;

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
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffect se = new StackEffect();
        se.addArrow();
        se.add(type.toSymbol());
        return se;
    }
}
