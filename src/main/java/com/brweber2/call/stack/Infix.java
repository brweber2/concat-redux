package com.brweber2.call.stack;

import com.brweber2.ast.Block;
import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.TypeStack;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Infix implements Call {
    @Override
    public void invoke(Stack stack) {
        Block items = (Block) stack.pop().object;
        Call left = items.getInstructions().get(0);
        Call operator =  items.getInstructions().get(1);
        Call right = items.getInstructions().get(2);
        left.invoke(stack);
        right.invoke(stack);
        operator.invoke(stack);
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(new Symbol(Block.class.getName()));
        stackEffect.addArrow();
        stackEffect.add(new Symbol(Object.class.getName())); // todo figure out number and types that will result from calling block
        return stackEffect;
    }
}
