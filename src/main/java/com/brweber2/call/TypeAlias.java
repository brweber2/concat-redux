package com.brweber2.call;

import com.brweber2.run.Call;
import com.brweber2.run.Instructions;
import com.brweber2.run.Stack;
import com.brweber2.ast.StackEffect;
import com.brweber2.lex.Symbol;
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
            Symbol type = (Symbol) stack.pop();
            Symbol alias = (Symbol) stack.pop();
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

    @Override
    public Instructions getInstructions() {
        return new Instructions();
    }
}
