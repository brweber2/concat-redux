package com.brweber2.call;

import com.brweber2.Call;
import com.brweber2.CheckedType;
import com.brweber2.Instructions;
import com.brweber2.Stack;
import com.brweber2.vocab.Vocabulary;

import java.util.List;

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
        return Vocabulary.getCurrent().findWord(word); // todo memoize
    }

    @Override
    public void invoke(Stack stack) {
        getCall().invoke(stack);
    }

    @Override
    public List<CheckedType> getInputTypes() {
        return getCall().getInputTypes();
    }

    @Override
    public List<CheckedType> getOutputTypes() {
        return getCall().getOutputTypes();
    }

    @Override
    public Instructions getInstructions() {
        return getCall().getInstructions();
    }
}
