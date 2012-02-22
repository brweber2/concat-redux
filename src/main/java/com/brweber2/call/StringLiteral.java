package com.brweber2.call;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.JavaType;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class StringLiteral implements Call {
    
    private String str;

    public StringLiteral(String str) {
        this.str = str;
    }

    @Override
    public void invoke(Stack stack) {
        stack.push( new JavaType(String.class),str);
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect se = new StackEffect();
        se.addArrow();
        se.add(new Symbol(String.class.getName()));
        return se;
    }

    @Override
    public String toString() {
        return '"' + str + '"';
    }
}
