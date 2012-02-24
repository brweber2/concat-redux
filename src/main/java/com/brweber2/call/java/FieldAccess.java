package com.brweber2.call.java;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.CheckedType;
import com.brweber2.type.JavaType;
import com.brweber2.type.TypeStack;

import java.lang.reflect.Field;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class FieldAccess implements Call {

    protected Field f;
    protected boolean staticField;

    // static field
    public FieldAccess(Field f, boolean staticField) {
        this.f = f;
        this.staticField = staticField;
    }

    @Override
    public void invoke(Stack stack) {
        if (staticField) {
            // static field
            try {
                stack.pop();
                stack.pop();
                stack.push(new JavaType(f.getType()), f.get(null));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to get value for static field " + f);
            }
        } else {
            // instance field
            try {
                stack.pop();
                stack.pop();
                Object instance = stack.pop().object;
                stack.push(new JavaType(f.getType()), f.get(instance));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to get value for field " + f);
            }
        }
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffect stackEffect = new StackEffect();
        if ( !staticField)
        {
            stackEffect.add(new Symbol(f.getDeclaringClass().getName()));
        }
        stackEffect.add(new Symbol(Symbol.class.getName()));
        stackEffect.add(new Symbol(Symbol.class.getName()));
        stackEffect.addArrow();
        stackEffect.add(new Symbol(f.getType().getName()));
        return stackEffect;
    }
}
