package com.brweber2.call;

import com.brweber2.CheckedType;
import com.brweber2.Invoke;
import com.brweber2.Stack;
import com.brweber2.ast.Block;
import com.brweber2.ast.StackEffect;
import com.brweber2.lex.Symbol;
import com.brweber2.type.JavaType;
import com.brweber2.vocab.Vocabulary;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class DefineCall extends Invoke {
    
    public DefineCall(Symbol name, StackEffect stackEffect, Block block) {
        super(Arrays.<CheckedType>asList(new JavaType(Symbol.class),new JavaType(StackEffect.class),new JavaType(Block.class)), Collections.<CheckedType>emptyList());
        Invoke invoke = new Invoke(stackEffect);
        invoke.setInstructions(stackEffect.getInstructions(), block.getInstructions());
        Vocabulary.getCurrent().register(name.symbol,invoke);
    }

    @Override
    public void invoke(Stack thisStack) {

    }
}
