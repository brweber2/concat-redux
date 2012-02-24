package com.brweber2.call;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.TypeStack;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class PrintlnCall implements Call {

    @Override
    public void invoke(Stack stack) {
        Object object = stack.pop().object;
        System.out.println("Hello " + object + "!");
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(new Symbol(Object.class.getName()));
        stackEffect.addArrow();
        return stackEffect;
    }
}
