package com.brweber2.call;

import com.brweber2.Call;
import com.brweber2.CheckedType;
import com.brweber2.Instructions;
import com.brweber2.Stack;
import com.brweber2.ast.StackEffect;
import com.brweber2.lex.Symbol;
import com.brweber2.lex.Token;
import com.brweber2.type.JavaType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Literal<T> implements Call {

    protected T literal;
    private Class<T> c;
    protected CheckedType type;

    public Literal(T literal, Class<? extends T> c) {
        this.literal = literal;
        type = new JavaType(c);
    }

    @Override
    public void invoke(Stack stack) {
        stack.push( literal );
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect se = new StackEffect();
        se.addArrow();
        se.add(new Symbol(c.getName()));
        return se;
    }

    @Override
    public Instructions getInstructions() {
        return new Instructions();
    }
}
