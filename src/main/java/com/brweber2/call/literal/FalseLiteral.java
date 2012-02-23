package com.brweber2.call.literal;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.JavaType;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class FalseLiteral implements Call {
    @Override
    public void invoke(Stack stack) {
        stack.push(new JavaType(Boolean.class),Boolean.FALSE);
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        stackEffect.addArrow();
        stackEffect.add(new Symbol(Boolean.class.getName()));
        return stackEffect;
    }
}
