package com.brweber2.call;

import com.brweber2.run.Invoke;
import com.brweber2.run.Stack;
import com.brweber2.type.CheckedType;
import com.brweber2.type.JavaType;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class FieldAccess extends Invoke {
    
    protected Field f;
    
    // static field
    public FieldAccess( Field f ) {
        super(Collections.<CheckedType>emptyList(), Arrays.<CheckedType>asList(new JavaType(f.getType())));
        this.f = f;
    }
    
    // instance field
    public FieldAccess( CheckedType instanceType, Field f )
    {
        super( Arrays.asList( instanceType ), Arrays.<CheckedType>asList(new JavaType(f.getType())));
        this.f = f;
    }

    @Override
    protected List getOutputs(Stack thisStack) {
        if ( stackEffect.getInputTypes().isEmpty() )
        {
            // static field
            try {
                return Arrays.asList(f.get(null));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to get value for static field " + f);
            }
        }
        else
        {
            // instance field
            try {
                return Arrays.asList(f.get(thisStack.pop()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to get value for field " + f);
            }
        }
    }
}
