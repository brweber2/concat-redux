package com.brweber2.call;

import com.brweber2.CheckedType;
import com.brweber2.Invoke;
import com.brweber2.Stack;
import com.brweber2.type.JavaType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class MethodCall extends Invoke {
    
    protected Method m;
    
    public MethodCall(List<CheckedType> inputTypes, Method m) {
        super(inputTypes, Arrays.<CheckedType>asList(new JavaType(m.getReturnType())));
    }

    @Override
    protected List getOutputs(Stack stack) {
        try {
            Object instance = stack.pop();
            Object[] args = stack.popAll().toArray();
            return Arrays.asList(m.invoke(instance, args));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to invoke method " + m,e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Unable to invoke method " + m,e);
        }
    }
}
