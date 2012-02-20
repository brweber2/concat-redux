package com.brweber2.call;

import com.brweber2.Call;
import com.brweber2.CheckedType;
import com.brweber2.Instructions;
import com.brweber2.Stack;
import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Statement;
import com.brweber2.vocab.Vocabulary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class WordCall implements Call {
    
    private String word;
    private List<Call> args = new ArrayList<Call>();
    
    public WordCall(Statement statement) {
        word = statement.getName();
        List pieces = statement.getPieces();
        for (Object o : pieces.subList(0,pieces.size()-1)) {
            if ( o instanceof Call )
            {
                args.add((Call)o);
            }
            else
            {
                args.add(new IdentityCall(o));
            }
        }
    }
    
    private Call getCall()
    {
        return Vocabulary.getCurrent().findWord(word); // todo memoize
    }

    @Override
    public void invoke(Stack stack) {
        for (Call arg : args) {
            arg.invoke(stack);
        }
        getCall().invoke(stack);
    }


    @Override
    public StackEffect getStackEffect() {
        return getCall().getStackEffect();
    }

    @Override
    public Instructions getInstructions() {
        return getCall().getInstructions();
    }
}
