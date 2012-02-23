package com.brweber2.call.word;

import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.ast.StackEffect;
import com.brweber2.type.JavaType;
import com.brweber2.vocab.Vocabulary;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class TypeAlias implements Call {
    @Override
    public void invoke(Stack stack) {
        try {
            Symbol type = (Symbol) stack.pop().object;
            Symbol alias = (Symbol) stack.pop().object;
            Vocabulary.getCurrent().alias(alias.symbol,new JavaType(Class.forName(type.symbol)));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to alias type.",e);
        }
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(new Symbol(Symbol.class.getName()));
        stackEffect.add(new Symbol(Symbol.class.getName()));
        stackEffect.addArrow();
        return stackEffect;
    }
}
