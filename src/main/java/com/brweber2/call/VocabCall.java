package com.brweber2.call;

import com.brweber2.Call;
import com.brweber2.Instructions;
import com.brweber2.Stack;
import com.brweber2.ast.StackEffect;
import com.brweber2.lex.Symbol;
import com.brweber2.vocab.Vocabulary;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class VocabCall implements Call {
    
    @Override
    public void invoke(Stack stack) {
        Symbol symbol = (Symbol) stack.pop();
        Vocabulary.setVocab(symbol.symbol);
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(new Symbol(Symbol.class.getName()));
        stackEffect.addArrow();
        return stackEffect;
    }

    @Override
    public Instructions getInstructions() {
        return new Instructions();
    }
}
