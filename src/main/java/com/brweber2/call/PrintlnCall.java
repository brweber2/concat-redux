package com.brweber2.call;

import com.brweber2.CheckedType;
import com.brweber2.Invoke;
import com.brweber2.Stack;
import com.brweber2.argument.StringArgument;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class PrintlnCall extends Invoke {

    public PrintlnCall() {
        super(Arrays.<CheckedType>asList(new StringArgument()), Collections.<CheckedType>emptyList());
    }

    @Override
    public void invoke(Stack stack) {
        super.invoke(stack);
        Object object = stack.pop();
        System.out.println("Hello " + object + "!");
    }
}
