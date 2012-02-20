package com.brweber2.call;

import com.brweber2.Invoke;
import com.brweber2.Stack;
import com.brweber2.ast.Block;
import com.brweber2.ast.StackEffect;
import com.brweber2.lex.Symbol;
import com.brweber2.vocab.Vocabulary;

import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class DefineCall extends Invoke {
    
    private Symbol name;
    private StackEffect stackEffect;
    private Block block;

    public DefineCall(Symbol name, StackEffect stackEffect, Block block) {
        super(stackEffect.getInputTypes(),stackEffect.getOutputTypes());
        setInstructions( stackEffect.getInstructions(), block.getInstructions() );
        Vocabulary.getCurrent().register(name.symbol,this);
    }

}
