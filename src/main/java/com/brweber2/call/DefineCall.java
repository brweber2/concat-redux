package com.brweber2.call;

import com.brweber2.run.Call;
import com.brweber2.run.Invoke;
import com.brweber2.run.Stack;
import com.brweber2.ast.Block;
import com.brweber2.ast.StackEffect;
import com.brweber2.lex.Symbol;
import com.brweber2.vocab.Vocabulary;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class DefineCall implements Call {
    
    public DefineCall(Symbol name, StackEffect stackEffect, Block block) {
        Vocabulary.getCurrent().register(name.symbol,new Invoke(stackEffect, block.getInstructions()));
    }

    @Override
    public void invoke(Stack thisStack) {
        thisStack.pop();
        thisStack.pop();
        thisStack.pop();
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(new Symbol(Symbol.class.getName()));
        stackEffect.add(new Symbol(StackEffect.class.getName()));
        stackEffect.add(new Symbol(Block.class.getName()));
        stackEffect.addArrow();
        return stackEffect;
    }
}
