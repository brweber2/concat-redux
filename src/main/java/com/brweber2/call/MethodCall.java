package com.brweber2.call;

import com.brweber2.ast.StackEffect;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.CheckedType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class MethodCall implements Call {
    
    protected Method m;
    protected CheckedType type;
    
    public MethodCall(List<CheckedType> inputTypes, Method m) {
    }

    @Override
    public void invoke(Stack stack) {
        try {
            Object instance = stack.pop();
            Object[] args = stack.popAll().toArray();
            stack.push(type, m.invoke(instance, args));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to invoke method " + m,e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Unable to invoke method " + m,e);
        }
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        stackEffect.addArrow();
        return stackEffect; // todo fix this whole class
    }
}
