package com.brweber2.call.literal;

import com.brweber2.ast.StackEffect;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.StackEffectType;
import com.brweber2.type.TypeStack;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class StackEffectLiteral implements Call {
    
    private StackEffect stackEffect;

    public StackEffectLiteral(StackEffect stackEffect) {
        this.stackEffect = stackEffect;
    }

    @Override
    public void invoke(Stack stack) {
        stack.push(new StackEffectType(stackEffect),stackEffect);
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        return stackEffect;
    }
}
