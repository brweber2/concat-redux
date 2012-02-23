package com.brweber2.call.java;

import com.brweber2.ast.StackEffect;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.CheckedType;

import java.lang.reflect.Field;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class FieldAccess implements Call {

    protected Field f;
    protected CheckedType type;

    // static field
    public FieldAccess(Field f) {
        this.f = f;
    }

    // instance field
    public FieldAccess(CheckedType instanceType, Field f) {
        this.f = f;
    }

    @Override
    public void invoke(Stack stack) {
        if (getStackEffect().getInputTypes().isEmpty()) {
            // static field
            try {
                stack.push(type, f.get(null));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to get value for static field " + f);
            }
        } else {
            // instance field
            try {
                stack.push(type, f.get(stack.pop()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to get value for field " + f);
            }
        }
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        stackEffect.addArrow();
        return stackEffect; // todo wrong, fix me
    }
}
