package com.brweber2.call;

import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.ast.Items;
import com.brweber2.ast.StackEffect;
import com.brweber2.vocab.Vocabulary;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Use implements Call {
    @Override
    public void invoke(Stack stack) {
        // from <vocab> :use [<words>] .
        Items items = (Items) stack.pop().object;
        Symbol vocab = (Symbol) stack.pop().object;
        Symbol from = (Symbol) stack.pop().object;
        if ( !"from".equals(from.symbol) )
        {
            throw new RuntimeException("Invalid use statement! (from is not in the correct place)");
        }

        for (Object o : items.getItems()) {
            Symbol wordName = (Symbol) o;
            Call word = Vocabulary.findWord( vocab, wordName );
            Vocabulary.getCurrent().register( wordName.symbol, word );
        }
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(new Symbol(Symbol.class.getName()));
        stackEffect.add(new Symbol(Symbol.class.getName()));
        stackEffect.add(new Symbol(Items.class.getName()));
        stackEffect.addArrow();
        return stackEffect;
    }
}
