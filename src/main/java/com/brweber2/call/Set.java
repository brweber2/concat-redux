package com.brweber2.call;

import com.brweber2.ast.StackEffect;
import com.brweber2.lex.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Instructions;
import com.brweber2.run.Stack;
import com.brweber2.lex.Var;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Set implements Call {

    @Override
    public void invoke(Stack stack) {
        Var name = (Var) stack.pop().object;
        Stack.TypeObject object = stack.pop();
        stack.set(name.var,object);
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(new Symbol(Var.class.getName()));
        stackEffect.add(new Symbol(Object.class.getName()));
        stackEffect.addArrow();
        stackEffect.add(new Symbol(Object.class.getName()));
        return stackEffect;
    }

    @Override
    public Instructions getInstructions() {
        return new Instructions();
    }
}
