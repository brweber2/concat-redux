package com.brweber2.call;

import com.brweber2.run.Invoke;
import com.brweber2.run.Stack;
import com.brweber2.type.CheckedType;
import com.brweber2.type.JavaType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class ConstructorCall extends Invoke {
    
    protected Constructor c;
    
    public ConstructorCall(List<CheckedType> inputTypes, Constructor c) {
        super(inputTypes, Arrays.<CheckedType>asList(new JavaType(c.getDeclaringClass())) );
        this.c = c;
    }

    @Override
    protected List getOutputs(Stack thisStack) {
        try {
            return Arrays.asList(c.newInstance(thisStack.popAll().toArray()));
        } catch (InstantiationException e) {
            throw new RuntimeException("Unable to create new instance of " + c.getName(),e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to create new instance of " + c.getName(),e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Unable to create new instance of " + c.getName(),e);
        }
    }
}
