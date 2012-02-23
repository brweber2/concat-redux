package com.brweber2.call.stack;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Dup implements Call {
    @Override
    public void invoke(Stack stack) {
        Stack.TypeObject typeObject = stack.pop();
        stack.push(typeObject.type,typeObject.object);
        stack.push(typeObject.type,typeObject.object);
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(new Symbol(Object.class.getName()));
        stackEffect.addArrow();
        stackEffect.add(new Symbol(Object.class.getName()));
        stackEffect.add(new Symbol(Object.class.getName()));
        return stackEffect;
    }
}
