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
public class Get implements Call {

    @Override
    public void invoke(Stack stack) {
        Var toGet = (Var) stack.pop().object;
        Stack.TypeObject typeObject = stack.get(toGet.var);
        stack.push( typeObject.type,  typeObject.object );
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(new Symbol(Var.class.getName()));
        stackEffect.addArrow();
        stackEffect.add(new Symbol(Object.class.getName())); // todo some type info is lost here?
        return stackEffect;
    }

    @Override
    public Instructions getInstructions() {
        return new Instructions();
    }
}
