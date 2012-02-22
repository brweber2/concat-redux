package com.brweber2.call;

import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.CheckedType;
import com.brweber2.ast.StackEffect;
import com.brweber2.lex.Symbol;
import com.brweber2.type.JavaType;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Literal<T> implements Call {

    protected T literal;
    private Class<? extends T> c;
    protected CheckedType type;

    public Literal(T literal, Class<? extends T> c) {
        this.literal = literal;
        type = new JavaType(c);
        this.c = c;
    }

    @Override
    public void invoke(Stack stack) {
        stack.push( type, literal );
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect se = new StackEffect();
        se.addArrow();
        se.add(new Symbol(c.getName()));
        return se;
    }
}
