package com.brweber2.call.word;

import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Statement;
import com.brweber2.type.JavaType;
import com.brweber2.vocab.Vocabulary;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class WordCall implements Call {
    
    private String word;
    
    public WordCall(String word) {
        this.word = word;
    }
    
    private Call getCall()
    {
        Call wordCall = Vocabulary.getCurrent().findWord(word); // todo memoize
        if ( wordCall == null )
        {
            throw new RuntimeException("No such word " + word + " found in scope.");
        }
        return wordCall;
    }

    @Override
    public void invoke(Stack stack) {
        getCall().invoke(stack);
    }


    @Override
    public StackEffect getStackEffect() {
        return getCall().getStackEffect();
    }

    @Override
    public String toString() {
        return word;
    }
}
