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
public class Push implements Call {
    @Override
    public void invoke(Stack stack) {
        
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(typeStack.peek(-1).toSymbol()); // todo is this right?
        stackEffect.addArrow();
        stackEffect.add(typeStack.peek(-1).toSymbol());
        return stackEffect;
    }
}
