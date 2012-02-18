package com.brweber2.call;

import com.brweber2.CheckedType;
import com.brweber2.Invoke;
import com.brweber2.Stack;
import com.brweber2.type.JavaType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Set extends Invoke {
    public Set(CheckedType type) {
        super(Arrays.<CheckedType>asList(new JavaType(String.class), type), Collections.<CheckedType>emptyList());
    }

    @Override
    protected List getOutputs(Stack stack) {
        String name = (String) stack.pop();
        Object object = stack.pop();
        stack.set(name,object);
        return Collections.emptyList();
    }
}
