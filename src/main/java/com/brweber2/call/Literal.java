package com.brweber2.call;

import com.brweber2.Call;
import com.brweber2.Stack;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Literal<T> implements Call {

    protected T literal;

    public Literal(T literal) {
        this.literal = literal;
    }

    @Override
    public void invoke(Stack stack) {
        stack.push( literal );
    }
}
