package com.brweber2.call;

import com.brweber2.run.Call;
import com.brweber2.run.Stack;
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
        Symbol symbol = (Symbol) stack.pop().object;
        Vocabulary.setVocab(symbol.symbol);
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(new Symbol(Symbol.class.getName()));
        stackEffect.addArrow();
        return stackEffect;
    }

}
