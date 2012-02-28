package com.brweber2.call.stack;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.ast.Var;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.TypeStack;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Set implements Call {

    // "foo" @bar set

    @Override
    public void invoke(Stack stack) {
        Var name = (Var) stack.pop().object;
        Stack.TypeObject object = stack.pop();
        stack.set(name.var,object);
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(typeStack.peek(-2).toSymbol());
        stackEffect.add(new Symbol(Var.class.getName()));
        stackEffect.addArrow();
        return stackEffect;
    }
}
