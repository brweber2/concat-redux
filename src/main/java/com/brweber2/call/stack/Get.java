package com.brweber2.call.stack;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.ast.Var;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.TypeStack;
import com.brweber2.vocab.Vocabulary;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Get implements Call {

    // @foo get

    @Override
    public void invoke(Stack stack) {
        Var toGet = (Var) stack.pop().object;
        Stack.TypeObject typeObject = stack.get(toGet.var);
        stack.push( typeObject.type,  typeObject.object );
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(new Symbol(Var.class.getName()));
        stackEffect.addArrow();
        stackEffect.add(typeStack.peek(-1).toSymbol());
        return stackEffect;
    }
}
