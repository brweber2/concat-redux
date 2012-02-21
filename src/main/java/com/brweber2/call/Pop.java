package com.brweber2.call;

import com.brweber2.ast.StackEffect;
import com.brweber2.lex.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Instructions;
import com.brweber2.run.Stack;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Pop implements Call
{
    @Override
    public void invoke(Stack stack) {
        stack.pop();
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        stackEffect.addArrow();
        stackEffect.add(new Symbol(Object.class.getName()));
        return stackEffect;
    }

    @Override
    public Instructions getInstructions() {
        return new Instructions();
    }
}