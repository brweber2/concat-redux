package com.brweber2.call;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Swap implements Call {
    @Override
    public void invoke(Stack stack) {
        Stack.TypeObject a = stack.pop();
        Stack.TypeObject b = stack.pop();
        stack.push(a.type,a.object);
        stack.push(b.type,b.object);
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect se = new StackEffect();
        se.add(new Symbol(Object.class.getName()));
        se.add(new Symbol(Object.class.getName()));
        se.addArrow();
        se.add(new Symbol(Object.class.getName()));
        se.add(new Symbol(Object.class.getName()));
        return se;
    }
}
