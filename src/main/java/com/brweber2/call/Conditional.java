package com.brweber2.call;

import com.brweber2.ast.Block;
import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Conditional implements Call {
    @Override
    public void invoke(Stack stack) {
        Block falseBlock = (Block) stack.pop().object;
        Block trueBlock = (Block) stack.pop().object;
        Boolean bool = (Boolean) stack.pop().object;
        if ( bool )
        {
            for (Call call : trueBlock.getInstructions()) {
                call.invoke(stack);
            }
        }
        else
        {
            for (Call call : falseBlock.getInstructions()) {
                call.invoke(stack);
            }
        }
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(new Symbol(Block.class.getName()));
        stackEffect.add(new Symbol(Block.class.getName()));
        stackEffect.add(new Symbol(Boolean.class.getName()));
        stackEffect.addArrow();
        stackEffect.add(new Symbol(Object.class.getName()));
        return stackEffect;
    }
}
