package com.brweber2.call;

import com.brweber2.CheckedType;
import com.brweber2.Invoke;
import com.brweber2.Stack;
import com.brweber2.type.JavaType;

import java.util.Arrays;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Get extends Invoke {
    public Get(CheckedType type) {
        super(Arrays.<CheckedType>asList(new JavaType(String.class)), Arrays.asList(type));
    }

    @Override
    protected List getOutputs(Stack stack) {
        String toGet = (String) stack.pop();
        return Arrays.asList( stack.get( toGet ) );
    }
}
