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
public class PrintlnCall extends Invoke {

    public PrintlnCall() {
        super(Arrays.<CheckedType>asList(new JavaType(String.class)), Collections.<CheckedType>emptyList());
    }

    @Override
    protected List getOutputs(Stack stack) {
        Object object = stack.pop();
        System.out.println("Hello " + object + "!");
        return Collections.emptyList();
    }
}
