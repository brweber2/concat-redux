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
public class NumberLiteral implements Call {
    
    private Number number;

    public NumberLiteral(Number number) {
        this.number = number;
    }

    @Override
    public void invoke(Stack stack) {
        stack.push(new JavaType(number.getClass()),number);
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect se = new StackEffect();
        se.addArrow();
        se.add(new Symbol(Number.class.getName()));
        return se;
    }

    @Override
    public String toString() {
        return number.toString();
    }
}
