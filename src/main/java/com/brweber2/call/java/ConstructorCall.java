package com.brweber2.call.java;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.CheckedType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class ConstructorCall implements Call {
    
    protected Constructor c;
    protected CheckedType type;
    protected int args;
    
    public ConstructorCall(List<CheckedType> inputTypes, Constructor c) {
        this.c = c;
        // todo all wrong... fix me later
    }

    @Override
    public void invoke(Stack stack) {
        try {
            List<Object> parameters = new ArrayList<Object>();
            for (int i = 0; i < args; i++ ) {
                parameters.add(stack.pop().object);
            }
            Object object = c.newInstance(parameters.toArray());
            stack.push(type, object);
        } catch (InstantiationException e) {
            throw new RuntimeException("Unable to create new instance of " + c.getName(),e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to create new instance of " + c.getName(),e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Unable to create new instance of " + c.getName(),e);
        }        
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        for (int i = 0; i < args; i++ ) {
            stackEffect.add(new Symbol(Object.class.getName())); // todo losing type safety all over the place here?
        }
        stackEffect.add(new Symbol(Object.class.getName()));
        stackEffect.addArrow();
        stackEffect.add(new Symbol(Object.class.getName()));
        return stackEffect;
    }
}
