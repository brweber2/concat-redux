package com.brweber2.call.stack;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.TypeStack;

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
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffect se = new StackEffect();
        Symbol a = typeStack.peek(-1).toSymbol();
        Symbol b = typeStack.peek(-2).toSymbol();
        se.add(b);
        se.add(a);
        se.addArrow();
        se.add(a);
        se.add(b);
        return se;
    }
}
