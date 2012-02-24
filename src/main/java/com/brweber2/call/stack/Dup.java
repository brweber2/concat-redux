package com.brweber2.call.stack;

import com.brweber2.ast.StackEffect;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.CheckedType;
import com.brweber2.type.TypeStack;

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
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffect stackEffect = new StackEffect();
        CheckedType type = typeStack.peek(-1);
        stackEffect.add(type.toSymbol());
        stackEffect.addArrow();
        stackEffect.add(type.toSymbol());
        stackEffect.add(type.toSymbol());
        return stackEffect;
    }
}
